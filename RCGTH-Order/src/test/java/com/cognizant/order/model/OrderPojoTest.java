package com.cognizant.order.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


class OrderPojoTest {


	@Test
	void testOrdersGettersSettersConstructorsToString() { 

		ProductPojo productPojo=new ProductPojo(1, "phone", "Electronics", 10000, "Y", "Y", "N", "Y");
		List<ProductPojo> productPojoList=new ArrayList<>();productPojoList.add(productPojo);
		OrderPojo orderPojo1 = new OrderPojo(1,"XYZ",productPojoList, 10000);
		OrderPojo orderPojo2 = new OrderPojo();
		OrderPojo orderPojo3 = new OrderPojo(1,"XYZ", 10000);
		
		orderPojo2.setOrderId(1);
		orderPojo2.setProducts(productPojoList);
		orderPojo2.setOrderName("XYZ");
		orderPojo2.setTotalPrice(10000);
		
		
		assertEquals(1,orderPojo1.getOrderId());
		assertEquals(productPojoList,orderPojo1.getProducts());
		assertEquals("XYZ",orderPojo1.getOrderName());
		assertEquals(10000,orderPojo3.getTotalPrice());
		
		
		assertEquals("OrderPojo(orderId=1, orderName=XYZ, products=[ProductPojo(productId=1, productName=phone, productType=Electronics, productPrice=10000, returnpolicy=Y, fragility=Y, expiry=N, warranty=Y)], totalPrice=10000)", orderPojo1.toString());
	}


}
