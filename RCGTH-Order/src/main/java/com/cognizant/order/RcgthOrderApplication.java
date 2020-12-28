package com.cognizant.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RcgthOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcgthOrderApplication.class, args);
	}

}
