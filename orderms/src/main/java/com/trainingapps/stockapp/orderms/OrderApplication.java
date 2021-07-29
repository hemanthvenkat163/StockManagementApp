package com.trainingapps.stockapp.orderms;

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
public class OrderApplication {

	private static final Logger Log = LoggerFactory.getLogger(OrderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);

		Log.info("***Repository added successfully");
		Log.info("***Service method tested successfully");

	}
	
	@LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
