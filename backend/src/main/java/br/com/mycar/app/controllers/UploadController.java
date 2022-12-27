package br.com.mycar.app.controllers;

import br.com.mycar.app.dtos.UploadImageRequestDTO;
import br.com.mycar.app.dtos.UploadResponseDTO;
import br.com.mycar.app.exceptions.ContentTypeException;
import br.com.mycar.app.exceptions.EmptyFileException;
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
    public ResponseEntity<UploadResponseDTO> newImageUploadRequest(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) throw new EmptyFileException("Você não enviou nenhum arquivo.");

        if(!(file.getOriginalFilename().endsWith(".png") || file.getOriginalFilename().endsWith(".jpg") ||
                file.getOriginalFilename().endsWith(".jpeg"))) {
            throw new ContentTypeException("Extensão não permitida.");
        }
        UploadImageRequestDTO uploadImageRequestDTO = new UploadImageRequestDTO();
        return new ResponseEntity<>(storageService.uploadFile(file, uploadImageRequestDTO), HttpStatus.OK);
    }
}
