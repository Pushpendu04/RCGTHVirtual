package com.cognizant.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.order.clients.IdentifyProfileClient;
import com.cognizant.order.entity.Orders;
import com.cognizant.order.entity.Product;
import com.cognizant.order.exception.OrderAlreadyExistsException;
import com.cognizant.order.exception.OrderNotFoundException;
import com.cognizant.order.exception.ProductTypeNotFoundException;
import com.cognizant.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private IdentifyProfileClient client;

	@Override
	public Orders saveOrders(Orders orders) throws OrderAlreadyExistsException, ProductTypeNotFoundException {
		try {
			Orders order = client.matchProfile(orders);

			if (orderRepository.findById(orders.getOrderId()).isPresent()) {
				throw new OrderAlreadyExistsException("Order already exists!!");
			}

			List<Product> productList = order.getProducts();
			int sum = productList.stream().mapToInt(Product::getProductPrice).sum();
			order.setTotal(sum);
			return orderRepository.save(order);
		} catch (Exception e) {
			throw new ProductTypeNotFoundException("Product Type Not Found!");
		}
	}

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> order = orderRepository.findAll();
		return order;

	}

	@Override
	public Orders updateOrders(Orders order) throws OrderNotFoundException, ProductTypeNotFoundException {
		Optional<Orders> orders = orderRepository.findById(order.getOrderId());
		Orders updateOrders = new Orders();
		try {
			if (orders.isPresent()) {
				Orders input = client.matchProfile(order);
				Orders updateOrder = orders.get();
				updateOrder.setOrderName(input.getOrderName());
				updateOrder.setProducts(input.getProducts());
				updateOrders = orderRepository.save(updateOrder);
			} else {
				throw new OrderNotFoundException("Order not found with id:" + order.getOrderId());
			}
			return updateOrders;
		} catch (Exception e) {
			throw new ProductTypeNotFoundException("Product Type Not Found!");
		}
	}

	@Override
	public String deleteOrders(int orderId) throws OrderNotFoundException {
		Optional<Orders> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			orderRepository.deleteById(orderId);
		} else {
			throw new OrderNotFoundException("Order not found with id:" + orderId);
		}
		return "Order deleted with id: " + orderId;
	}

}
