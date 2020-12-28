package com.cognizant.identifyprofiles.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProfileTest {

	@Test
	void testOrdersGettersSettersConstructorsToString() { 
		Profile profile1=new Profile("Electronics", "Y", "Y", "N", "Y");
		Profile profile2=new Profile();
	
		profile2.setProfiletype("Electronics");
		profile2.setFragility("Y");
		profile2.setReturnpolicy("Y");
		profile2.setExpiry("N");
		profile2.setWarranty("Y");
		
		assertEquals("Electronics",profile1.getProfiletype());
		assertEquals("Y",profile1.getFragility());
		assertEquals("Y",profile1.getReturnpolicy());
		assertEquals("N",profile1.getExpiry());
		assertEquals("Y", profile1.getWarranty());
		
		
		assertEquals("Profile(profiletype=Electronics, returnpolicy=Y, fragility=Y, expiry=N, warranty=Y)", profile1.toString());
	}

}
