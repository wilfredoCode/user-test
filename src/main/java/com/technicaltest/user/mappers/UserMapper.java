package com.technicaltest.user.mappers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.technicaltest.user.models.dtos.PhoneDTO;
import com.technicaltest.user.models.dtos.UserRegistryRequestDTO;
import com.technicaltest.user.models.dtos.UserRegistryResponseDTO;
import com.technicaltest.user.models.entities.PhoneEntity;
import com.technicaltest.user.models.entities.UserEntity;

public class UserMapper {
    public static UserEntity requestDtoToEntity(UserRegistryRequestDTO userRequestBody, String token){
        Date dateNow = new Date();
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequestBody.getName());
        userEntity.setEmail(userRequestBody.getEmail());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        userEntity.setPassword(bcrypt.encode(userRequestBody.getPassword()));
        userEntity.setCreated(dateNow);
        userEntity.setModified(dateNow);
        userEntity.setLastLogin(dateNow);
        userEntity.setToken(token);
        List<PhoneDTO> phonesDto = userRequestBody.getPhones();
        List<PhoneEntity> phonesEntities = new ArrayList<>();

        for (PhoneDTO phoneDto : phonesDto) {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setNumber(phoneDto.getNumber());
            phoneEntity.setCitycode(phoneDto.getCitycode());
            phoneEntity.setContrycode(phoneDto.getContrycode());
            phonesEntities.add(phoneEntity);
        }
        userEntity.setPhones(phonesEntities);
        return userEntity;
    }
    public static UserRegistryResponseDTO entityToResponseDto(UserEntity userEntity, String token){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        final String CREATED = dateFormatter.format(userEntity.getCreated());
        final String MODIFIED = dateFormatter.format(userEntity.getModified());
        final String LAST_LOGIN = dateFormatter.format(userEntity.getLastLogin());
        
        List<PhoneEntity> phonesEntities = userEntity.getPhones();
        List<PhoneDTO> phonesDto = new ArrayList<>();

        for (PhoneEntity phoneEntity : phonesEntities) {
            PhoneDTO phoneDto = new PhoneDTO();
            phoneDto.setNumber(phoneEntity.getNumber());
            phoneDto.setCitycode(phoneEntity.getCitycode());
            phoneDto.setContrycode(phoneEntity.getContrycode());
            phonesDto.add(phoneDto);
        }
        UserRegistryResponseDTO responseDto = UserRegistryResponseDTO.builder()
        .created(CREATED)
        .modified(MODIFIED)
        .lastLogin(LAST_LOGIN)
        .token(token)
        .isActive(true)
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .phones(phonesDto)
        .build();
        return responseDto;
    }
}
