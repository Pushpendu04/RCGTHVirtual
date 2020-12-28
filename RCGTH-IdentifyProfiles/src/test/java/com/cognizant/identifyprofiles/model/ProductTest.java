package com.cognizant.identifyprofiles.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void testOrdersGettersSettersConstructorsToString() { 

		Product product1=new Product(1, "phone", "Electronics", 10000, "Y", "Y", "N", "Y");
		Product product2=new Product();
		
		product2.setProductId(1);
		product2.setProductName("phone");
		product2.setProductType("Electronics");
		product2.setProductPrice(10000);
		product2.setFragility("Y");
		product2.setWarranty("Y");
		product2.setExpiry("N");
		product2.setReturnpolicy("Y");
		
		assertEquals(1,product1.getProductId());
		assertEquals("phone",product1.getProductName());
		assertEquals("Electronics",product1.getProductType());
		assertEquals("Y",product1.getFragility());
		assertEquals("Y",product1.getWarranty());
		assertEquals("N",product1.getExpiry());
		assertEquals("Y",product1.getReturnpolicy());
		
		
		assertEquals("Product(productId=1, productName=phone, productType=Electronics, productPrice=10000, returnpolicy=Y, fragility=Y, expiry=N, warranty=Y)", product1.toString());
	}

}
