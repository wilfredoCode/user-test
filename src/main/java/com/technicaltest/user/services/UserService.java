package com.technicaltest.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.technicaltest.user.exception.CustomHttpException;
import com.technicaltest.user.mappers.UserMapper;
import com.technicaltest.user.models.dtos.UserRegistryRequestDTO;
import com.technicaltest.user.models.dtos.UserRegistryResponseDTO;
import com.technicaltest.user.models.entities.UserEntity;
import com.technicaltest.user.repositories.UserRepository;
import com.technicaltest.user.validators.UserValidators;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;

    public ResponseEntity<UserRegistryResponseDTO> registryUser(@RequestBody UserRegistryRequestDTO userRequestBody) {
        log.info("Start registryUser");
        UserValidators.requestRegistryUser(userRequestBody);
        log.info("Start generate JWT");
        String jwt = jwtService.getToken(userRequestBody.getName());
        UserEntity userEntity = UserMapper.requestDtoToEntity(userRequestBody, jwt);
        UserEntity userEntityResponse = null;
        try{
            log.info("Start save data in user database");
            userEntityResponse = userRepository.save(userEntity);
        }
        catch(Exception exception){
            log.error(exception.getLocalizedMessage());
            if(exception instanceof DataIntegrityViolationException){
            String message = "";
                if(exception.getMessage().contains(userRequestBody.getEmail())){
                    message = "El correo ya registrado";
                    log.error(message);
                    throw new CustomHttpException(HttpStatus.BAD_REQUEST, message);
                }
                message = "Datos incorrectos";
                log.error(message);
                throw new CustomHttpException(HttpStatus.BAD_REQUEST, message);
            }
            throw exception;
        }
        
        UserRegistryResponseDTO userResponseDto = UserMapper.entityToResponseDto(userEntityResponse,jwt);
        log.info("Success registryUser");
        return new ResponseEntity<UserRegistryResponseDTO>(userResponseDto, HttpStatus.CREATED);
    }
}
