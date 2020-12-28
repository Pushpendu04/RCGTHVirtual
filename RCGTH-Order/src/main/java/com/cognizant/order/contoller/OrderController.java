package com.cognizant.order.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.order.entity.Orders;
import com.cognizant.order.entity.Product;
import com.cognizant.order.exception.OrderAlreadyExistsException;
import com.cognizant.order.exception.OrderNotFoundException;
import com.cognizant.order.exception.ProductTypeNotFoundException;
import com.cognizant.order.model.OrderPojo;
import com.cognizant.order.model.ProductPojo;
import com.cognizant.order.redis.OrderDaoCache;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderDaoCache cache;

	@GetMapping
	public List<OrderPojo> getAllOrders() {
		List<Orders> order = cache.getAllOrders(); 
		List<OrderPojo> listOrderPojo = new ArrayList<OrderPojo>();
		for (Orders orders : order) {
			OrderPojo orderpojo = new OrderPojo();
			orderpojo.setOrderId(orders.getOrderId());
			orderpojo.setOrderName(orders.getOrderName());
			List<Product> productList = orders.getProducts();
			List<ProductPojo> productPojoList = new ArrayList<ProductPojo>();
			for (Product prod : productList) {
				ProductPojo productPojo = new ProductPojo();
				productPojo.setProductId(prod.getProductId());
				productPojo.setProductName(prod.getProductName());
				productPojo.setProductType(prod.getProductType());
				productPojo.setProductPrice(prod.getProductPrice());
				productPojo.setReturnpolicy(prod.getReturnpolicy());
				productPojo.setFragility(prod.getFragility());
				productPojo.setWarranty(prod.getWarranty());
				productPojo.setExpiry(prod.getExpiry());
				productPojoList.add(productPojo);
			}
			orderpojo.setProducts(productPojoList);
			int sum=productPojoList.stream().mapToInt(ProductPojo::getProductPrice).sum();
			orderpojo.setTotalPrice(sum);
			listOrderPojo.add(orderpojo);
		}
		return listOrderPojo;
	}

	@PostMapping
	public Orders insertOrder(@RequestBody Orders orders) throws OrderAlreadyExistsException, ProductTypeNotFoundException {
		return cache.saveOrders(orders);
	}

	@PutMapping
	public Orders updateOrders(@RequestBody Orders order) throws OrderNotFoundException, ProductTypeNotFoundException {
		return cache.updateOrders(order);
	}

	@DeleteMapping("/{orderId}")
	public String deleteOrder(@PathVariable int orderId) throws OrderNotFoundException {
		return cache.deleteOrders(orderId);
	}

}
