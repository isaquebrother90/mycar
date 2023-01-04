package br.com.mycar.app.dtos.car;

import br.com.mycar.app.abstracts.AbstractVehicle;
import br.com.mycar.app.entities.FileReference;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {
    private String chassis;
    @Enumerated(EnumType.STRING)
    private AbstractVehicle.VehicleType vehicleType;
    private String year;
    private String model;
    private String brand;
    private Long id;
    private BigDecimal price;
    private FileReference fileReference;
}
