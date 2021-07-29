package com.trainingapps.stockapp.stockms.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 * 
 * Class to convert in Date Format
 * 
 * @author harii
 *
 */
@Component
public class DateConverter {
    private final String pattern = "dd-MM-yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    public String toText(LocalDate date) {
        String text=formatter.format(date);
        return text;
    }

    public LocalDate toDate(String text) {
        LocalDate date = LocalDate.parse(text, formatter);
        return date;
    }
    
}