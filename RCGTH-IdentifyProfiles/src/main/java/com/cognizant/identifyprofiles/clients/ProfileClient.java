package com.cognizant.identifyprofiles.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cognizant.identifyprofiles.model.Profile;


@FeignClient(name = "RCGTH-Profile-Microservice" ,url = "http://localhost:8083")
public interface ProfileClient {
	
	@GetMapping("/profile/")
	public List<Profile> getAllProfiles();

}
