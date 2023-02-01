package com.gameshop.service;

import com.gameshop.domain.ShippingAddress;
import com.gameshop.domain.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
