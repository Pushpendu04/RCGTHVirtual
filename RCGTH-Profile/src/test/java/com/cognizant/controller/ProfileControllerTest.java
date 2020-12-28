package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.entity.Profile;
import com.cognizant.exception.ProfileAlreadyExistException;
import com.cognizant.exception.ProfileNotFoundException;
import com.cognizant.redis.ProfileDaoCache;
import com.cognizant.service.ProfileService;


@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {


	@InjectMocks
	private transient ProfileController profileController;
	@Mock
	private transient  ProfileDaoCache cache;
	@Mock
	private transient ProfileService profileService;
	
	@Test
	final void testGetAllProfiles()  {
		Profile profile=new Profile("Electronics", "Y", "Y", "N", "Y");
		List<Profile> profileList=new ArrayList<>(); profileList.add(profile);
		when(cache.getAllProfiles()).thenReturn(profileList);
		List<Profile> expected = profileController.getAllProfiles();
		assertEquals(expected,profileList);
	}
	
	@Test
	final void testGetOneProfiles() throws ProfileNotFoundException  {
		Profile profile=new Profile("Electronics", "Y", "Y", "N", "Y");
		when(profileService.getOneProfiles("Electronics")).thenReturn(profile);
		Profile expected = profileController.getOneProfiles("Electronics");
		assertEquals(expected,profile);
	}
	
	@Test
	final void testInsertProfiles() throws ProfileAlreadyExistException  {
		Profile profile=new Profile("Electronics", "Y", "Y", "N", "Y");
		when(cache.insertProfiles(profile)).thenReturn(profile);
		Profile expected = profileController.insertProfiles(profile);
		assertEquals(expected,profile);
	}
	
	@Test
	final void testUpdateProfiles() throws ProfileNotFoundException   {
		Profile profile=new Profile("Electronics", "Y", "Y", "N", "Y");
		when(cache.updateProfiles(profile)).thenReturn(profile);
		Profile expected = profileController.updateProfiles(profile);
		assertEquals(expected,profile);
	}
	
	@Test
	final void testDeleteProfiles() throws ProfileNotFoundException   {
		String expected = profileController.deleteProfiles("Electronics");
		assertEquals(expected,null);
	}
}
