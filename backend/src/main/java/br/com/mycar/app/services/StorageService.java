package br.com.mycar.app.services;

import br.com.mycar.app.dtos.UploadImageRequestDTO;
import br.com.mycar.app.dtos.UploadResponseDTO;
import br.com.mycar.app.entities.FileReference;
import br.com.mycar.app.repositories.FileReferenceRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

@Service
@Log4j2
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    FileReferenceRepository fileReferenceRepository;

    @Autowired
    private AmazonS3 s3Client;

    public UploadResponseDTO uploadFile(UploadImageRequestDTO requestDTO, FileReference fileReference) {

        File base64Converted = base64ToImage(requestDTO);

        //String fileName = fileReference.getId() + "_" + base64Converted.getName();
        s3Client.putObject(new PutObjectRequest(bucketName, fileReference.getPath(),
                base64Converted));

        base64Converted.delete();

        return new UploadResponseDTO(fileReference.getId(), fileReference.getName());
    }

    private File base64ToImage(UploadImageRequestDTO requestDTO) {
        byte[] base64Bytes = DatatypeConverter.parseBase64Binary(requestDTO.getImageBase64());
        String base64Type = requestDTO.getContentType().substring(6);//getBase64Type(base64);
        String tempFile = "C:\\Windows\\Temp\\" + requestDTO.getFileName() + "." + base64Type;
        File file = new File(tempFile);

        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(base64Bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return file;
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
}
