package com.technicaltest.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomHttpException.class)
    public ResponseEntity<ExceptionResp> handlerOfCustomHttp(CustomHttpException ex) {
        ExceptionResp exceptionBody = new ExceptionResp(ex.getMessage());
        log.error("[ResponseEntityExceptionHandler] -> " + ex.getLocalizedMessage());
        return ResponseEntity.status(ex.getStatus()).body(exceptionBody);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResp> handlerOfGenericException(Exception ex) {
        ExceptionResp exceptionBody = new ExceptionResp("unexpected error");
        log.error("[ResponseEntityExceptionHandler] -> " + ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }

}

