package com.gameshop.service;

import com.gameshop.domain.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	
	void removeById(Long id);
}
