package com.technicaltest.user.models.dtos;

import lombok.Data;

@Data
public class PhoneDTO {
    private String number;
    private String citycode;
    private String contrycode;
}
