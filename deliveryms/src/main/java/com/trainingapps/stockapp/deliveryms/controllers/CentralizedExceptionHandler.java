package com.trainingapps.stockapp.deliveryms.controllers;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainingapps.stockapp.deliveryms.exceptions.DeliveryNotFoundException;
import com.trainingapps.stockapp.deliveryms.exceptions.InvalidStatusException;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DeliveryNotFoundException.class)
	public String handleDeliveryNotFound(DeliveryNotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidStatusException.class)
	public String handleInvalidStatus(InvalidStatusException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e){
        return e.getMessage();
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public String handlePSQLException(SQLException e){
        return e.getMessage();
    }
	
}
