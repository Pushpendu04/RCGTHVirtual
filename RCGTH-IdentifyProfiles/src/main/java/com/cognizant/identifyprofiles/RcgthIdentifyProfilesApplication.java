package com.cognizant.identifyprofiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RcgthIdentifyProfilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcgthIdentifyProfilesApplication.class, args);
	}

}
