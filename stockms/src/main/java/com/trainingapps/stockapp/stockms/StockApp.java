package com.trainingapps.stockapp.stockms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class StockApp {

	private static final Logger Log = LoggerFactory.getLogger(StockApp.class);
    public static void main(String[] args) {
    	
        SpringApplication.run(StockApp.class, args);
        Log.info("Repository created...");
        Log.info("Service methods excueted successfully...");
        
        
    }
}

