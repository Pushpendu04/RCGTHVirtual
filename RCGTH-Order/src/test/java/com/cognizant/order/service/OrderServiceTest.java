package com.cognizant.order.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.order.clients.IdentifyProfileClient;
import com.cognizant.order.entity.Orders;
import com.cognizant.order.entity.Product;
import com.cognizant.order.exception.OrderAlreadyExistsException;
import com.cognizant.order.exception.OrderNotFoundException;
import com.cognizant.order.exception.ProductTypeNotFoundException;
import com.cognizant.order.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTest {

	@MockBean
	private IdentifyProfileClient identifyProfileClient;
	
	@InjectMocks
	private OrderServiceImpl orderService;
	@MockBean
	private OrderRepository orderRepository;

	Product product1=new Product(1, "phone", "Electronics", 10000, "Y", "Y", "N", "Y");
	List<Product> productList1=new ArrayList<Product>();List<Product> productList2=new ArrayList<Product>();
	Product product2=new Product(1, "phone", "El", 10000,  "Y", "Y", "N", "Y");
	

	@BeforeEach
	void setUp() {
		productList1.add(product1);productList2.add(product2);
	}	

	@Test
	void testSaveOrders() throws OrderAlreadyExistsException, ProductTypeNotFoundException  {		
		Orders order1=new Orders(1,"XYZ",productList1 , 1);
		Mockito.when(identifyProfileClient.matchProfile(order1)).thenReturn(order1);
		Mockito.when(orderRepository.save(order1)).thenReturn(order1);
		assertEquals(order1,orderService.saveOrders(order1));		
	}
//	@Test
//	void testSaveOrdersOrderAlreadyExists() throws OrderAlreadyExistsException  {		
//		Orders order=new Orders(1,"XYZ",productList1 , 1);orderRepository.save(order);
//		Mockito.when(identifyProfileClient.matchProfile(order)).thenReturn(order);
//		Mockito.when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
//		Mockito.when(orderRepository.save(order)).thenReturn(order);
//		assertThrows(OrderAlreadyExistsException.class, () -> orderService.saveOrders(order));		
//	}
	@Test
	void testSaveOrdersProductTypeNotFound() throws OrderAlreadyExistsException, ProductTypeNotFoundException  {		
		Orders order=new Orders(1,"XYZ",productList1 , 1);Orders order2=new Orders(1,"XYZ",productList2 , 1);
		Mockito.when(identifyProfileClient.matchProfile(order2)).thenReturn(order);
		assertThrows(ProductTypeNotFoundException.class, () -> orderService.saveOrders(order));	
	}
	
	@Test
	void testGetAllOrders() {		
		Orders order=new Orders(1,"XYZ",productList1 , 1);
		Mockito.when(orderRepository.findAll()).thenReturn(List.of(order));
		assertEquals(List.of(order),orderService.getAllOrders());		
	}
	
	
	@Test
	void testUpdateOrders() throws OrderNotFoundException, ProductTypeNotFoundException {		
		Orders order=new Orders(1,"XYZ",productList1 , 1);
		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		Mockito.when(identifyProfileClient.matchProfile(order)).thenReturn(order);
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		assertEquals(order,orderService.updateOrders(order));		
	}
	@Test
	void testUpdateOrdersProductTypeNotFoundException() throws OrderNotFoundException, ProductTypeNotFoundException {		
		Orders order=new Orders(1,"XYZ",productList1 , 1);Orders order2=new Orders(1,"XYZ",productList2 , 1);
		Mockito.when(identifyProfileClient.matchProfile(order2)).thenReturn(order);
		assertThrows(ProductTypeNotFoundException.class, () -> orderService.updateOrders(order));		
	}
	
	@Test
	void testDeleteOrders() throws OrderNotFoundException {		
		Orders order=new Orders(1,"XYZ",productList1 , 1);
		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		Mockito.doNothing().when(orderRepository).deleteById(1);
		assertEquals("Order deleted with id: 1",orderService.deleteOrders(1));		
	}
	@Test
	void testDeleteOrdersOrderNotFound() throws OrderNotFoundException {
		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.empty());
		Mockito.doNothing().when(orderRepository).deleteById(1);
		assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrders(1));
	}
}
