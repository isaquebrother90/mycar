package br.com.mycar.app.dtos;

import br.com.mycar.app.abstracts.AbstractVehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@JsonIgnoreProperties("fileReference")
public class CarRequestDTO {

    @JsonProperty("chassis")
    private String chassis;

    @Enumerated(EnumType.STRING)
    @JsonProperty("vehicleType")
    private AbstractVehicle.VehicleType vehicleType;

    @JsonProperty("year")
    private String year;

    @JsonProperty("model")
    private String model;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("image")
    private UploadImageRequestDTO uploadImageRequestDTO;

}
