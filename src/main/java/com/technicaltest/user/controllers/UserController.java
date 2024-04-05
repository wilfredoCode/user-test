package com.technicaltest.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.technicaltest.user.models.dtos.UserRegistryRequestDTO;
import com.technicaltest.user.models.dtos.UserRegistryResponseDTO;
import com.technicaltest.user.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserRegistryResponseDTO> register(@RequestBody UserRegistryRequestDTO userRequestBody){
       log.info("Start UserController");
       return userService.registryUser(userRequestBody);
    }
}
