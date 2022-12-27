package br.com.mycar.app.controllers;

import br.com.mycar.app.dtos.UploadImageRequestDTO;
import br.com.mycar.app.dtos.UploadResponseDTO;
import br.com.mycar.app.exceptions.ContentTypeException;
import br.com.mycar.app.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private final StorageService storageService;

    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/images")
    public ResponseEntity<UploadResponseDTO> newImageUploadRequest(@RequestParam("file")/* @AllowedFileExtensions({".png", ".jpg"})*/ MultipartFile file) {
        UploadImageRequestDTO uploadImageRequestDTO = new UploadImageRequestDTO();
        if(file.getName().endsWith(".png") || file.getName().endsWith(".jpg")){
            uploadImageRequestDTO.setFileName(org.apache.commons.io.FilenameUtils.getName(file.getOriginalFilename()));
            return new ResponseEntity<>(storageService.uploadFile(file, uploadImageRequestDTO.toDomain()), HttpStatus.OK);
        }
        else {
            try {
                System.out.println();
                throw new ContentTypeException("Extensão não permitida.");
            } catch (ContentTypeException e) {
                System.out.println(e.getMessage());
            }
        }
        UploadResponseDTO urd = new UploadResponseDTO(null, null);
        return new ResponseEntity<>(urd, HttpStatus.OK);
    }
}
