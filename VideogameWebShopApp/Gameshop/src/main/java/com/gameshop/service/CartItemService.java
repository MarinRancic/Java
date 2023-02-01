package com.gameshop.service;

import java.util.List;

import com.gameshop.domain.CartItem;
import com.gameshop.domain.Game;
import com.gameshop.domain.Order;
import com.gameshop.domain.ShoppingCart;
import com.gameshop.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addGameToCartItem(Game game, User user, int qty);	
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
