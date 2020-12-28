package com.cognizant.identifyprofiles.controller;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cognizant.identifyprofiles.exception.ProductTypeNotFoundException;
import com.cognizant.identifyprofiles.model.Orders;
import com.cognizant.identifyprofiles.model.Product;
import com.cognizant.identifyprofiles.service.IdentifyProfilesService;

@ExtendWith(MockitoExtension.class)
class IdentifyControllerTest {

	
	@InjectMocks
	private transient IdentifyProfilesController identifyProfilesController;
	@Mock
	private transient IdentifyProfilesService identifyProfilesService;



	@Test
	final void testMatchProfile() throws ProductTypeNotFoundException {
		Product product=new Product(1, "phone", "Electronics", 10000, "Y", "Y", "N", "Y");
		List<Product> productList=new ArrayList<Product>();productList.add(product);
		Orders order=new Orders(1,productList , "XYZ", 1) ;
		when(identifyProfilesService.matchProfile(order)).thenReturn(order);
		Orders expected = identifyProfilesController.matchProfile(order);
		assertEquals(expected,order);
	}
}

