package br.com.mycar.app.entities;

import br.com.mycar.app.abstracts.AbstractVehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car extends AbstractVehicle {
    private String image;
    private List<String> images;
    private BigDecimal price;
}
