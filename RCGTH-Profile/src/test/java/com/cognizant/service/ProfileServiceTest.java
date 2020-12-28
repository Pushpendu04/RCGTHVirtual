package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.cognizant.entity.Profile;
import com.cognizant.exception.ProfileAlreadyExistException;
import com.cognizant.exception.ProfileNotFoundException;
import com.cognizant.repo.ProfileRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProfileServiceTest {
	
	@InjectMocks
	private ProfileService profileService;
	@MockBean
	private ProfileRepository profileRepository;
	@MockBean
	private MessageChannel messageChannel;


	Profile profile=new Profile("Electronics", "Y", "Y", "N", "Y");
	List<Profile> profileList=new ArrayList<>(); 
	

	@BeforeEach
	void setUp() {
		profileList.add(profile);
	}	

	@Test
	void testGetAllProfiles()  {		
		Mockito.when(profileRepository.findAll()).thenReturn(profileList);
		assertEquals(profileList,profileService.getAllProfiles());		
	}
	
	@Test
	void testGetOneProfiles() throws ProfileNotFoundException  {		
		Mockito.when(profileRepository.findById("Electronics")).thenReturn(Optional.of(profile));
		assertEquals(profile,profileService.getOneProfiles("Electronics"));		
	}
	@Test
	void testGetOneProfilesProfileNotFound() throws ProfileNotFoundException  {	
		Mockito.when(profileRepository.findById("Electronics")).thenReturn(Optional.empty());
		assertThrows(ProfileNotFoundException.class, () -> profileService.getOneProfiles("Electronics"));	
	}
	
	@Test
	void testInsertProfiles() throws ProfileAlreadyExistException {
		Mockito.when(messageChannel.send(MessageBuilder.withPayload(profile.getProfiletype()+" Profile Inserted").build())).thenReturn(true);
		Mockito.when(profileRepository.save(profile)).thenReturn(profile);
		assertEquals(profile,profileService.insertProfiles(profile));	
	}

	@Test
	void testUpdateProfiles() throws ProfileNotFoundException  {		
		Mockito.when(messageChannel.send(MessageBuilder.withPayload(profile.getProfiletype()+" Profile Inserted").build())).thenReturn(true);
		Mockito.when(profileRepository.findById("Electronics")).thenReturn(Optional.of(profile));
		Mockito.when(profileRepository.save(profile)).thenReturn(profile);		
		assertEquals(profile,profileService.updateProfiles(profile));		
	}
	@Test
	void testUpdateProfilesProfileNotFound() throws ProfileNotFoundException  {		
		Mockito.when(profileRepository.findById("Electronics")).thenReturn(Optional.empty());		
		assertThrows(ProfileNotFoundException.class, () -> profileService.updateProfiles(profile));		
	}
	
	@Test
	void testDeleteProfiles() throws ProfileNotFoundException  {		
		Mockito.when(messageChannel.send(MessageBuilder.withPayload(profile.getProfiletype()+" Profile Inserted").build())).thenReturn(true);
		Mockito.when(profileRepository.findById("Electronics")).thenReturn(Optional.of(profile));
		Mockito.doNothing().when(profileRepository).deleteById("Electronics");	
		Mockito.when(messageChannel.send(MessageBuilder.withPayload(profile.getProfiletype()+" Profile Inserted").build())).thenReturn(true);
		assertEquals("Electronics Profile Deleted",profileService.deleteProfiles("Electronics"));		
	}
	@Test
	void testDeleteProfilesProfileNotFound() throws ProfileNotFoundException  {		
		Mockito.when(profileRepository.findById("Electronics")).thenReturn(Optional.empty());
		assertThrows(ProfileNotFoundException.class, () -> profileService.deleteProfiles("Electronics"));		
	}

}
