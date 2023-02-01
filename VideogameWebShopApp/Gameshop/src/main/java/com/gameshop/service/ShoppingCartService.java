package com.gameshop.service;

import com.gameshop.domain.ShoppingCart;

public interface ShoppingCartService {
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);
}
