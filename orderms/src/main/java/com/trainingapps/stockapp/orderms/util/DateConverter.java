package com.trainingapps.stockapp.orderms.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateConverter {
    private final String pattern = "dd-MM-yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    /**
     * To convert date into text
     * @param date
     * @return
     */
    public String toText(LocalDate date) {
        String text=formatter.format(date);
        return text;
    }

    /**
     * To convert text to date format
     * @param text
     * @return
     */
    public LocalDate toDate(String text) {
        LocalDate date = LocalDate.parse(text, formatter);
        return date;
    }

}