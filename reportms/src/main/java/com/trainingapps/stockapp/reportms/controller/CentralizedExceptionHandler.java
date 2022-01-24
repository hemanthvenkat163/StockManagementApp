package com.trainingapps.stockapp.reportms.controller;

import java.time.format.DateTimeParseException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trainingapps.stockapp.reportms.exceptions.InvalidDatesException;
import com.trainingapps.stockapp.reportms.exceptions.InvalidEndDateException;
import com.trainingapps.stockapp.reportms.exceptions.InvalidEndPeriodException;
import com.trainingapps.stockapp.reportms.exceptions.InvalidStartPeriodException;
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
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidStartPeriodException.class)
    public String handleInvalidStartPeriodException(InvalidStartPeriodException e){
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEndPeriodException.class)
    public String handleInvalidEndPeriodException(InvalidEndPeriodException e){
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e){
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public String handleDateTimeParseException(DateTimeParseException e){
        return e.getMessage();
    }
    
}