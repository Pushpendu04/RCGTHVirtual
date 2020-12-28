package com.cognizant.order.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductPojoTest {

	@Test
	void testOrdersGettersSettersConstructorsToString() { 

		ProductPojo productPojo1=new ProductPojo(1, "phone", "Electronics", 10000, "Y", "Y", "N", "Y");
		ProductPojo productPojo2=new ProductPojo();
		
		productPojo2.setProductId(1);
		productPojo2.setProductName("phone");
		productPojo2.setProductType("Electronics");
		productPojo2.setProductPrice(10000);
		productPojo2.setFragility("Y");
		productPojo2.setWarranty("Y");
		productPojo2.setExpiry("N");
		productPojo2.setReturnpolicy("Y");
		
		assertEquals(1,productPojo1.getProductId());
		assertEquals("phone",productPojo1.getProductName());
		assertEquals("Electronics",productPojo1.getProductType());
		assertEquals("Y",productPojo1.getFragility());
		assertEquals("Y",productPojo1.getWarranty());
		assertEquals("N",productPojo1.getExpiry());
		assertEquals("Y",productPojo1.getReturnpolicy());
		
		
		assertEquals("ProductPojo(productId=1, productName=phone, productType=Electronics, productPrice=10000, returnpolicy=Y, fragility=Y, expiry=N, warranty=Y)", productPojo1.toString());
	}
}
