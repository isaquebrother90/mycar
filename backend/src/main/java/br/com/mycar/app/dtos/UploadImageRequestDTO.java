package br.com.mycar.app.dtos;

import br.com.mycar.app.entities.FileReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UploadImageRequestDTO {
    @NotBlank
    //@AllowedFileExtensions({"png","jpg"})
    @JsonIgnore
    private String fileName;

    @NotBlank
    private String contentType;

    //@NotNull
    @Min(1)
    @JsonIgnore
    private Long contentLength;

    public FileReference toDomain() {
        return FileReference.builder()
                .name(this.fileName)
                .contentType(this.contentType)
                .contentLength(this.contentLength)
                .type(FileReference.Type.IMAGE)
                .build();
    }
}
