package br.com.mycar.app.entities;

import br.com.mycar.app.abstracts.AbstractVehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class CarEntity extends AbstractVehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "file_reference_id")
    private FileReference fileReference;
    public CarEntity() {
    }

    public CarEntity(String chassis, VehicleType vehicleType, String year, String model, String brand/*, Long id*/, BigDecimal price, FileReference fileReference) {
        super(chassis, vehicleType, year, model, brand);
        Objects.requireNonNull(price);

        this.price = price;
        this.fileReference = fileReference;
    }
}
