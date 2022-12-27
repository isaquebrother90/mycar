package br.com.mycar.app.controllers;

import br.com.mycar.app.entities.CarEntity;
import br.com.mycar.app.entities.UserEntity;
import br.com.mycar.app.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class CarController {

    @Autowired
    CarService service;

    @PostMapping
    public ResponseEntity<CarEntity> store(@RequestBody CarEntity carEntity) {
        return new ResponseEntity<>(service.save(carEntity), HttpStatus.CREATED);
    }
}
