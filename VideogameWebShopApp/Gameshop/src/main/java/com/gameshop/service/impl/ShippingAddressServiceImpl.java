package com.gameshop.service.impl;

import org.springframework.stereotype.Service;

import com.gameshop.domain.ShippingAddress;
import com.gameshop.service.ShippingAddressService;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	public ShippingAddress setByUserShipping(com.gameshop.domain.UserShipping userShipping, com.gameshop.domain.ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
		shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
		
		return shippingAddress;
	}
}
