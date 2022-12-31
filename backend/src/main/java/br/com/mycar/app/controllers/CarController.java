package br.com.mycar.app.controllers;

import br.com.mycar.app.dtos.CarRequestDTO;
import br.com.mycar.app.dtos.CarResponseDTO;
import br.com.mycar.app.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    CarService service;

    @PostMapping
    public ResponseEntity<CarResponseDTO> store(@RequestBody @Valid CarRequestDTO requestDTO) {
        return new ResponseEntity<>(service.save(requestDTO), HttpStatus.CREATED);
    }

    //TODO To create get car controller
    //TODO To create get car by id controller
    //TODO To create delete car by id controller
    //TODO To create put or patch car by id controller
}
