package com.hackathon.stockservice.util;


import com.hackathon.stockservice.exceptions.ErrorDto;
import com.hackathon.stockservice.exceptions.StockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class StockServiceExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Exception handler if the stock code passed is not present.
	 * @param e
	 * @return
	 */
    @ExceptionHandler(StockNotFoundException.class)
    protected ResponseEntity<ErrorDto> handleWhileInvalidDigitCode(StockNotFoundException e){
        return new ResponseEntity<>(ErrorDto
                .builder()
                .errorCode(400)
                .errorMessage(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}