package com.trainingapps.stockapp.reportms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class DateConverter {
    private final String pattern = "dd-MM-yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    /**
     * converting date to string
     * @param date
     * @return
     */
    public String toText(LocalDate date) {
        String text=formatter.format(date);
        return text;
    }
    
    /**
     * convertig string to date
     * @param text
     * @return
     */
    public LocalDate toDate(String text) {
        LocalDate date = LocalDate.parse(text, formatter);
        return date;
    }
    
    /**
     * returning no of days between the dates
     * @param startDate
     * @param endDate
     * @return
     */
    public int days(LocalDate startDate, LocalDate endDate){
           long days=ChronoUnit.DAYS.between(startDate,endDate);
        return (int) days;
    }
    
    /**
     * returning the no of months between the dates
     * @param startDate
     * @param endDate
     * @return
     */
    public int months(LocalDate startDate, LocalDate endDate){
        long months=ChronoUnit.MONTHS.between(startDate,endDate);
        return (int)months;
    }
}
