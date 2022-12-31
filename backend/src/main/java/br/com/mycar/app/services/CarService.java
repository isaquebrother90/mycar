package br.com.mycar.app.services;

import br.com.mycar.app.dtos.CarRequestDTO;
import br.com.mycar.app.dtos.CarResponseDTO;
import br.com.mycar.app.entities.CarEntity;
import br.com.mycar.app.entities.FileReference;
import br.com.mycar.app.entities.UserEntity;
import br.com.mycar.app.entities.mappers.CarMapper;
import br.com.mycar.app.exceptions.ContentTypeException;
import br.com.mycar.app.exceptions.EmptyFileException;
import br.com.mycar.app.repositories.CarRepository;
import br.com.mycar.app.repositories.FileReferenceRepository;
import br.com.mycar.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    CarRepository carRepository;
    UserRepository userRepository;

    StorageService storageService;

    FileReferenceRepository fileReferenceRepository;

    CarMapper mapper;

    public CarService(CarRepository carRepository, UserRepository userRepository, StorageService storageService,
                      FileReferenceRepository fileReferenceRepository, CarMapper mapper) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
        this.fileReferenceRepository = fileReferenceRepository;
        this.mapper = mapper;
    }

    @Transactional
    public CarResponseDTO save(CarRequestDTO carRequestDTO) {

        Optional<String> base64Image = Optional.ofNullable(carRequestDTO.getUploadImageRequestDTO().getImageBase64());
        if (base64Image.isEmpty()) throw new EmptyFileException("You have not uploaded any files.");

        String contentType = carRequestDTO.getUploadImageRequestDTO().getContentType();
        if (!(contentType.endsWith("/png") || contentType.endsWith("/jpg") || contentType.endsWith("/jpeg"))) {
            throw new ContentTypeException("Extension not allowed.");
        }

        FileReference fileReference = new FileReference(carRequestDTO.getUploadImageRequestDTO().getFileName(),
                                                        carRequestDTO.getUploadImageRequestDTO().getContentType(),
                                                        carRequestDTO.getUploadImageRequestDTO().getContentLength(),
                                                        FileReference.Type.IMAGE);

        fileReferenceRepository.save(fileReference);

        //TODO To create mapper toEntity in the class CarMapper, so remove the mapper bellow.
        CarEntity carEntity = new CarEntity(carRequestDTO.getChassis(),
                                            carRequestDTO.getVehicleType(),
                                            carRequestDTO.getYear(),
                                            carRequestDTO.getModel(),
                                            carRequestDTO.getBrand(),
                                            carRequestDTO.getPrice(),
                                            fileReference);

        carRepository.save(carEntity);

        storageService.uploadFile(carRequestDTO.getUploadImageRequestDTO(), fileReference);

        return mapper.toDTO(carEntity);
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    //TODO To create search car service
    //TODO To create search car by id service
    //TODO To create remove car by id service
    //TODO To create update car by id service
    //TODO Throws exception NotFound to search car by id, to remove car by id and to update car by id
    //TODO To use Optional as much as possible to avoid null values
}
