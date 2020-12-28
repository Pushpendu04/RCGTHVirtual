package com.cognizant.order.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderPojo {
	private int orderId;
	private String orderName;
	private List<ProductPojo> products;
	private int totalPrice;
	public OrderPojo(int orderId,String orderName,int totalPrice)
	{
		this.orderId=orderId;this.orderName=orderName;this.totalPrice=totalPrice;
	}


}
