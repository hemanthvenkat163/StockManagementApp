package com.trainingapps.stockapp.stockms.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainingapps.stockapp.stockms.exceptions.FutureAvailableStockNotFound;
import com.trainingapps.stockapp.stockms.exceptions.InvalidStockId;
import com.trainingapps.stockapp.stockms.exceptions.QuantityOutOfBound;
import com.trainingapps.stockapp.stockms.exceptions.StockNotFoundException;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidStockId.class)
	public String idNotFound(InvalidStockId e)
	{
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(QuantityOutOfBound.class)
	public String stockOutOfBound(QuantityOutOfBound e)
	{
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(StockNotFoundException.class)
	public String stockNotFound(StockNotFoundException e)
	{
		return e.getMessage();
	}
	
	@ExceptionHandler(FutureAvailableStockNotFound.class)
	public String futureAvailableStockNotFound(FutureAvailableStockNotFound e)
	{
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e){
        return e.getMessage();
    }
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException e){
        return e.getMessage();
    }

}
