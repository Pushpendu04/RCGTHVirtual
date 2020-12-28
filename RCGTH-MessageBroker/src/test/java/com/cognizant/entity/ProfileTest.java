package com.cognizant.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ProfileTest {

	@Test
	void testOrdersGettersSettersConstructorsToString() { 
		Profile profile1=new Profile(1,"XYZ","Electronics");
		Profile profile2=new Profile();
	
		profile2.setProfileid(1);
		profile2.setProfilename("XYZ");
		profile2.setProfiletype("Electronics");
		
		
		assertEquals(1,profile1.getProfileid());
		assertEquals("XYZ",profile1.getProfilename());
		assertEquals("Electronics",profile1.getProfiletype());
		
		assertEquals("Profile(profileid=1, profilename=XYZ, profiletype=Electronics)", profile1.toString());
	}

}
