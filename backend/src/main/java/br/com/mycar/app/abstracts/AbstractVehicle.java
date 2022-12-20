package br.com.mycar.app.abstracts;

import br.com.mycar.app.utils.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractVehicle {
    private Long chassis;
    private VehicleType vehicleType;
    private String year;
    private String model;
    private String brand;
}
