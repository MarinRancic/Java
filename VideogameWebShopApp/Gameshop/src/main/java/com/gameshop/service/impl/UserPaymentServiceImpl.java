package com.gameshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.domain.UserPayment;
import com.gameshop.repository.UserPaymentRepository;
import com.gameshop.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).orElse(null);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
}
