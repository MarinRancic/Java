package com.gameshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.gameshop.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
}
