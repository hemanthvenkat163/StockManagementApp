package com.trainingapps.stockapp.suppliedstockms;

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
public class SuppliedStockmsApplication {
	private static final Logger Log = LoggerFactory.getLogger(SuppliedStockmsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SuppliedStockmsApplication.class, args);
		Log.info("Successfully Executed");
	}
	
	@LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
