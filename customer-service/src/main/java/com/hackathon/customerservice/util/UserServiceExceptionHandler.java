package com.hackathon.customerservice.util;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hackathon.customerservice.exceptions.ErrorDto;
import com.hackathon.customerservice.exceptions.FieldErrorDto;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;

import feign.FeignException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class UserServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    protected ResponseEntity<ErrorDto> handle(InvalidCredentialsException ex) {
        ErrorDto dto = new ErrorDto(401, ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        FieldErrorDto errors = new FieldErrorDto(400, "Validation failed", details);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<FieldErrorDto> handle(ConstraintViolationException e){
        List<String> errors = e.getConstraintViolations().stream().map(ConstraintViolation<?>::getMessage).collect(Collectors.toList());
        return new ResponseEntity<>(FieldErrorDto
                .builder()
                .errorCode(500)
                .errorMessage("Field error")
                .errors(errors)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<String> handle(FeignException e){
        return new ResponseEntity<>(e.contentUTF8(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}