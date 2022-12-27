package br.com.mycar.app.abstracts;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class AbstractVehicle {
    private String chassis;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private String year;
    private String model;
    private String brand;

    public AbstractVehicle() {
    }

    public AbstractVehicle(String chassis, VehicleType vehicleType, String year, String model, String brand) {
        Objects.requireNonNull(chassis);
        Objects.requireNonNull(vehicleType);
        Objects.requireNonNull(year);
        Objects.requireNonNull(model);
        Objects.requireNonNull(brand);

        this.chassis = chassis;
        this.vehicleType = vehicleType;
        this.year = year;
        this.model = model;
        this.brand = brand;
    }

    @Getter
    @AllArgsConstructor
    public enum VehicleType {
        CAR(1),
        TRUCK(2),
        PICKUP(3),
        VAN(4),
        SUV(5);

        private final int typeID;
    }
}
