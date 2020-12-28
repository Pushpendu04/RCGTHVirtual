package com.cognizant.order.model;

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
public class ProductPojo {
	
//	private int slno;
	private int productId;
	private String productName;
	private String productType;
	private int productPrice;
	private String returnpolicy;
	private String fragility;
	private String expiry;
	private String warranty;


}
