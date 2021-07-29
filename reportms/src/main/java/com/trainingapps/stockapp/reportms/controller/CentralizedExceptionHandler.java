package com.trainingapps.stockapp.reportms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainingapps.stockapp.reportms.exceptions.InvalidDatesException;
import com.trainingapps.stockapp.reportms.exceptions.InvalidEndDateException;
import com.trainingapps.stockapp.reportms.exceptions.ReportNotFoundException;


/**
 * Centralized Exception Handler
 * @author Hemanth Venkat
 *
 */
@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReportNotFoundException.class)
    public String handleReportNotFoundException(ReportNotFoundException e) {
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