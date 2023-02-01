package com.gameshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.gameshop.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{
	
}
