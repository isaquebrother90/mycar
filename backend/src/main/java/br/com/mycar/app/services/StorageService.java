package br.com.mycar.app.services;

import br.com.mycar.app.dtos.UploadImageRequestDTO;
import br.com.mycar.app.dtos.UploadResponseDTO;
import br.com.mycar.app.entities.FileReference;
import br.com.mycar.app.repositories.FileReferenceRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

@Service
@Log4j2
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    FileReferenceRepository fileReferenceRepository;

    @Autowired
    private AmazonS3 s3Client;

    public UploadResponseDTO uploadFile(MultipartFile file, UploadImageRequestDTO requestDTO/*FileReference fileReference*/) {
        requestDTO.setFileName(org.apache.commons.io.FilenameUtils.getName(file.getOriginalFilename()));
        FileReference fileReference = requestDTO.toDomain();

        fileReferenceRepository.save(fileReference);
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = fileReference.getId() + "_" + file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            fileObj.delete();
            return new UploadResponseDTO(fileReference.getId(), fileReference.getName());
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
