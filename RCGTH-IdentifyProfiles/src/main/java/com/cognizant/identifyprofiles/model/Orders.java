package com.cognizant.identifyprofiles.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Orders {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private List<Product> products;
	private String orderName;
	private int total;
	

}
