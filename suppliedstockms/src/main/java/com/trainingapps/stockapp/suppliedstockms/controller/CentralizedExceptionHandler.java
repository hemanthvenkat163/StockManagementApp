package com.trainingapps.stockapp.suppliedstockms.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidDatesException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidEndDateException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidSuppliedStockIdException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidSupplierIdDetailsException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.InvalidSupplierIdException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.SuppliedDetailsNotFoundException;
import com.trainingapps.stockapp.suppliedstockms.exceptions.SuppliedStockNotFoundException;

@RestControllerAdvice
public class CentralizedExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(SuppliedStockNotFoundException.class)
	public String handleSuppliedStockNotFoundException(SuppliedStockNotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidSuppliedStockIdException.class)
	public String handleInvalidSuppliedStockException(InvalidSuppliedStockIdException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidSupplierIdException.class)
	public String handleInvalidSupplierIdException(InvalidSupplierIdException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidSupplierIdDetailsException.class)
	public String handleInvalidSupplierIdDetailsException(InvalidSupplierIdDetailsException e) {
		return e.getMessage();
	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(SuppliedDetailsNotFoundException.class)
	public String handleSuppliedDetailsNotFoundException(SuppliedDetailsNotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e){
        return e.getMessage();
    }
	

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEndDateException.class)
    public String handleInvalidEndDateException(InvalidEndDateException e){
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDatesException.class)
    public String handleInvalidDatesException(InvalidDatesException e){
        return e.getMessage();
    }
}

