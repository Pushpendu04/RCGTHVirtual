package com.cognizant.identifyprofiles.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cognizant.identifyprofiles.clients.ProfileClient;
import com.cognizant.identifyprofiles.exception.ProductTypeNotFoundException;
import com.cognizant.identifyprofiles.model.Orders;
import com.cognizant.identifyprofiles.model.Product;
import com.cognizant.identifyprofiles.model.Profile;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class IdentifyProfileServiceTest {
	

	@MockBean
	ProfileClient profileClient;
	
	@InjectMocks
	private IdentifyProfilesServiceImpl identifyProfilesService;

	Profile profile1=new Profile("Electronics", "Y", "Y", "N", "Y");
	List<Profile> profileList1=new ArrayList<>();
	Profile profile2=new Profile("Food", "N", "Y", "Y", "N");
	List<Profile> profileList2=new ArrayList<>();
	Product product=new Product(1, "phone", "Electronics", 10000, "Y", "Y", "N", "Y");
	List<Product> productList=new ArrayList<Product>();
	Orders order=new Orders(1,productList , "XYZ", 1) ;

	@BeforeEach
	void setUp() {
		profileList1.add(profile1);
		profileList2.add(profile2);
		productList.add(product);
	}	

	@Test
	void testMatchProfile() throws ProductTypeNotFoundException {		
		Mockito.when(profileClient.getAllProfiles()).thenReturn(profileList1);
		assertEquals(order,identifyProfilesService.matchProfile(order));		
	}
	@Test
	void testMatchProfileProductNotFound() throws ProductTypeNotFoundException {		
		Mockito.when(profileClient.getAllProfiles()).thenReturn(profileList2);
		assertThrows(ProductTypeNotFoundException.class, () -> identifyProfilesService.matchProfile(order));
	}
}
