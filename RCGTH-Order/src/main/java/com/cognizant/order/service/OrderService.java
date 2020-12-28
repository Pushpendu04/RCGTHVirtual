package com.cognizant.order.service;

import java.util.List;

import com.cognizant.order.entity.Orders;
import com.cognizant.order.exception.OrderAlreadyExistsException;
import com.cognizant.order.exception.OrderNotFoundException;
import com.cognizant.order.exception.ProductTypeNotFoundException;

public interface OrderService {

	public Orders saveOrders(Orders orders) throws OrderAlreadyExistsException, ProductTypeNotFoundException;

	public List<Orders> getAllOrders();

	public Orders updateOrders(Orders order) throws OrderNotFoundException, ProductTypeNotFoundException;

	public String deleteOrders(int orderId) throws OrderNotFoundException;

}
