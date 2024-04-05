package com.technicaltest.user.models.dtos;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistryResponseDTO {
    private String created;
    private String modified;
    @JsonProperty("last_login")
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
