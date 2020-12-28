package com.cognizant.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.order.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
