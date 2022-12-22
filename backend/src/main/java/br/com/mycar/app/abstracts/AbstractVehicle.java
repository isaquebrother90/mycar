package br.com.mycar.app.abstracts;

import br.com.mycar.app.utils.VehicleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractVehicle {
    private String chassis;
    @JsonProperty("vehicleType")
    private VehicleType vehicleType;
    private String year;
    private String model;
    private String brand;
}
