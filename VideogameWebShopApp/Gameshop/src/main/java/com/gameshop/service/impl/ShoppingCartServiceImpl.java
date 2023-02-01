package com.gameshop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gameshop.domain.CartItem;
import com.gameshop.domain.ShoppingCart;
import com.gameshop.repository.ShoppingCartRepository;
import com.gameshop.service.CartItemService;
import com.gameshop.service.ShoppingCartService;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getGame().getInStockNumber() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
			}
		}
		
		shoppingCart.setGrandTotal(cartTotal);
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;
	}
	
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem: cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}
		
		shoppingCart.setGrandTotal(new BigDecimal(0));
		
		shoppingCartRepository.save(shoppingCart);
	} 
}
