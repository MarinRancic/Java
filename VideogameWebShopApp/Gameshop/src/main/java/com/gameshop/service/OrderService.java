package com.gameshop.service;

import com.gameshop.domain.BillingAddress;
import com.gameshop.domain.Order;
import com.gameshop.domain.Payment;
import com.gameshop.domain.ShippingAddress;
import com.gameshop.domain.ShoppingCart;
import com.gameshop.domain.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user);
	Order findById(Long id);
}
