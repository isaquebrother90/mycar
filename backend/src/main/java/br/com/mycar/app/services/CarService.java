package br.com.mycar.app.services;

import br.com.mycar.app.entities.CarEntity;
import br.com.mycar.app.entities.UserEntity;
import br.com.mycar.app.repositories.CarRepository;
import br.com.mycar.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    CarRepository carRepository;

    UserRepository userRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CarEntity save(CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
