package com.org.home.exception;

import com.org.home.exception.Error;
import com.org.home.exception.ZipCodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class WeatherControllerAdvice {

    @ExceptionHandler(ZipCodeNotFoundException.class)
    public ResponseEntity<Object> handleZipCodeNotFoundException(ZipCodeNotFoundException zcnfe) {
        Error error = new Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMsg(zcnfe.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        Error error = new Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMsg(exception.getConstraintViolations().iterator().next().getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
