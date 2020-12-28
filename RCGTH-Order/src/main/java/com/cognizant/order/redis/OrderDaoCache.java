package com.cognizant.order.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cognizant.order.entity.Orders;
import com.cognizant.order.exception.OrderAlreadyExistsException;
import com.cognizant.order.exception.OrderNotFoundException;
import com.cognizant.order.exception.ProductTypeNotFoundException;
import com.cognizant.order.service.OrderService;

@Repository
public class OrderDaoCache {

	public static final String HASH_KEY = "Order";

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate template;

	@Autowired
	OrderService service;

	@SuppressWarnings("unchecked")
	public Orders saveOrders(Orders orders) throws OrderAlreadyExistsException, ProductTypeNotFoundException {
		if (template.opsForHash().get(HASH_KEY, orders.getOrderId()) != null) {
			throw new OrderAlreadyExistsException("Order Already Exist!");
		} else {
			Orders order = service.saveOrders(orders);
			template.opsForHash().put(HASH_KEY, order.getOrderId(), order);
			return order;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Orders> getAllOrders() {
		List<Orders> order = template.opsForHash().values(HASH_KEY);
		if(order.isEmpty()) {
			return (List<Orders>) service.getAllOrders();
		}else {
			return order;
		}
	}

	@SuppressWarnings("unchecked")
	public String deleteOrders(int orderId) throws OrderNotFoundException {
		service.deleteOrders(orderId);
		template.opsForHash().delete(HASH_KEY, orderId);
		return "Order deleted with id: " + orderId;
	}

	@SuppressWarnings("unchecked")
	public Orders updateOrders(Orders orders) throws OrderNotFoundException, ProductTypeNotFoundException {
		if (template.opsForHash().get(HASH_KEY, orders.getOrderId()) == null) {
			throw new OrderNotFoundException("Order Not Found!");
		} else {
			Orders updatedOrder=service.updateOrders(orders);
			template.opsForHash().put(HASH_KEY, updatedOrder.getOrderId(), updatedOrder);
			return updatedOrder;
		}
	}

}
