package com.technicaltest.user.validators;

import org.springframework.http.HttpStatus;

import com.technicaltest.user.exception.CustomHttpException;
import com.technicaltest.user.models.dtos.UserRegistryRequestDTO;
import com.technicaltest.user.utils.ValidatorsUtils;

public class UserValidators {
    public static void requestRegistryUser(UserRegistryRequestDTO userRequestBody){
        if(!ValidatorsUtils.isEmailValid(userRequestBody.getEmail()))
            throw new CustomHttpException(HttpStatus.BAD_REQUEST, "El correo no es válido");

        if(!ValidatorsUtils.isPasswordValid(userRequestBody.getPassword()))
            throw new CustomHttpException(HttpStatus.BAD_REQUEST, "La contraseña no es válida");

    }
}
