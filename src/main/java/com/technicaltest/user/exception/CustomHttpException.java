package com.technicaltest.user.exception;
import org.springframework.http.HttpStatus;

public class CustomHttpException extends RuntimeException {
    private final HttpStatus status;
    private final String description;
    public CustomHttpException(HttpStatus status,String description) {
        super(description);
        this.description = description;
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return status.toString();
    }
}

