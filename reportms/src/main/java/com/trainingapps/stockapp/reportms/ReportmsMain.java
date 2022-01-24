package com.trainingapps.stockapp.reportms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class ReportmsMain {

	private static final Logger Log = LoggerFactory.getLogger(ReportmsMain.class);

	public static void main(String[] args) {
		SpringApplication.run(ReportmsMain.class, args);
		Log.info("***Repository added successfully");
		Log.info("***service methods are running successfully");
		
		Log.info("***Application starte successfully ");
	}
	
	@LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
