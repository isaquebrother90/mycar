package br.com.mycar.app.entities.mappers;

import br.com.mycar.app.dtos.car.CarResponseDTO;
import br.com.mycar.app.entities.CarEntity;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarResponseDTO toDTO(CarEntity entity) {
        CarResponseDTO responseDTO = new CarResponseDTO();
        responseDTO.setChassis(entity.getChassis());
        responseDTO.setVehicleType(entity.getVehicleType());
        responseDTO.setYear(entity.getYear());
        responseDTO.setModel(entity.getModel());
        responseDTO.setBrand(entity.getBrand());
        responseDTO.setId(entity.getId());
        responseDTO.setPrice(entity.getPrice());
        responseDTO.setFileReference(entity.getFileReference());
        return responseDTO;
    }
}
