package com.cognizant.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	

}
