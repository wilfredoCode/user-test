package com.technicaltest.user.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExceptionResp {
    @JsonProperty("mensaje")
    private final String message;

    public ExceptionResp(String message) {
        this.message = message;
    }
}
