package com.cognizant.identifyprofiles.service;

import com.cognizant.identifyprofiles.exception.ProductTypeNotFoundException;
import com.cognizant.identifyprofiles.model.Orders;

public interface IdentifyProfilesService  {
	
	public Orders matchProfile(Orders orders) throws ProductTypeNotFoundException;
	
	
}
