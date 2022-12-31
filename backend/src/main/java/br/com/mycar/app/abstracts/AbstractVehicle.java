package br.com.mycar.app.abstracts;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractVehicle {
    private String chassis;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private String year;
    private String model;
    private String brand;

    @Getter
    @AllArgsConstructor
    public enum VehicleType {
        CAR,
        TRUCK,
        PICKUP,
        VAN,
        SUV;
    }
}
