package com.example.websocketchat.exception.advice;

import com.example.websocketchat.exception.ApiException;
import com.example.websocketchat.exception.NoUserFoundException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            NoSuchElementException.class,
            NoUserFoundException.class
    })
    public ResponseEntity<ApiException> handleNoElementFound(Exception exception){
        HttpStatus status = Objects.requireNonNull(AnnotationUtils.findAnnotation(
                exception.getClass(),
                ResponseStatus.class
        )).code();

        final ApiException apiException = new ApiException(
                exception.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, status);
    }

}
