package com.cognizant.identifyprofiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.identifyprofiles.exception.ProductTypeNotFoundException;
import com.cognizant.identifyprofiles.model.Orders;
import com.cognizant.identifyprofiles.service.IdentifyProfilesService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/identifyprofiles")
@Log4j2
public class IdentifyProfilesController {

	@Autowired
	IdentifyProfilesService identifyProfilesService;

	@PostMapping("/matchProfile")
	public Orders matchProfile(@RequestBody Orders orders) throws ProductTypeNotFoundException {

		Orders matchProfile = identifyProfilesService.matchProfile(orders);
		log.info(matchProfile);
		return matchProfile;

	}

}
