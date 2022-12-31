package br.com.mycar.app.repositories;

import br.com.mycar.app.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    //TODO To create find car by id or by other entity attribute
}
