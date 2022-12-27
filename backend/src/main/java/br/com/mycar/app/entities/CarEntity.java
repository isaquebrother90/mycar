package br.com.mycar.app.entities;

import br.com.mycar.app.abstracts.AbstractVehicle;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NonNull
public class CarEntity extends AbstractVehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //private String image;
    //private List<String> images;
    @OneToOne(cascade = CascadeType.ALL)
    private FileReference image;
    private BigDecimal price;

    public CarEntity() {
    }

    public CarEntity(String chassis, VehicleType vehicleType, String year, String model, String brand, Long id, FileReference image, BigDecimal price) {
        super(chassis, vehicleType, year, model, brand);
        Objects.requireNonNull(image);
        Objects.requireNonNull(price);

        this.id = id;
        this.image = image;
        this.price = price;
    }

    public CarEntity(Long id, FileReference image, BigDecimal price) {
        this.id = id;
        this.image = image;
        this.price = price;
    }
}
