package com.technicaltest.user.models.dtos;

import java.util.List;
import lombok.Data;

@Data
public class UserRegistryRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
