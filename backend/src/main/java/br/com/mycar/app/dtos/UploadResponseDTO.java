package br.com.mycar.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UploadResponseDTO {
    private UUID fileReferenceId;
    private String name;
}
