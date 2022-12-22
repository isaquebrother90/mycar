package br.com.mycar.app.controllers;

import br.com.mycar.app.entities.UserEntity;
import br.com.mycar.app.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    CarService carService;

    public UserController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> storeUser(@RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(carService.saveUser(userEntity), HttpStatus.CREATED);
    }
}
