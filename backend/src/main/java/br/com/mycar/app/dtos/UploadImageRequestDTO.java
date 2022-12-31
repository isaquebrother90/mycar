package br.com.mycar.app.dtos;

import br.com.mycar.app.entities.FileReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadImageRequestDTO {
    @NotBlank
    @JsonProperty("fileName")
    private String fileName;

    @NotBlank
    @JsonProperty("imageBase64")
    private String imageBase64;

    @NotBlank
    @JsonProperty("contentType")
    private String contentType;

    @Min(1)
    @JsonProperty("contentLength")
    private Long contentLength;

}
