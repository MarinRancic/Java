package com.gameshop.service;

import com.gameshop.domain.UserShipping;

public interface UserShippingService {
	UserShipping findById(Long id);
	
	void removeById(Long id);
}
