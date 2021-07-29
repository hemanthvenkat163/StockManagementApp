package com.trainingapps.stockapp.orderms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainingapps.stockapp.orderms.exceptions.InvalidPeriodException;
import com.trainingapps.stockapp.orderms.exceptions.OrderNotFoundException;


@RestControllerAdvice
public class CentralizedExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public String handleOrderNotFound(OrderNotFoundException e) {
        return e.getMessage();
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidPeriodException.class)
	public String handleInvalidPeriod(InvalidPeriodException e) {
		return e.getMessage();
	}
	
}
