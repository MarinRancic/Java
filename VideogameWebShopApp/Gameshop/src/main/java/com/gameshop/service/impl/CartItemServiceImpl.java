package com.gameshop.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.domain.CartItem;
import com.gameshop.domain.Game;
import com.gameshop.domain.GameToCartItem;
import com.gameshop.domain.Order;
import com.gameshop.domain.ShoppingCart;
import com.gameshop.domain.User;
import com.gameshop.repository.CartItemRepository;
import com.gameshop.repository.GameToCartItemRepository;
import com.gameshop.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private GameToCartItemRepository gameToCartItemRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart){
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getGame().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
	}
	
	public CartItem addGameToCartItem(Game game, User user, int qty) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem: cartItemList) {
			if(game.getId() == cartItem.getGame().getId()) {
				cartItem.setQty(cartItem.getQty() + qty);
				cartItem.setSubtotal(new BigDecimal(game.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem ();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setGame(game);
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(game.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		GameToCartItem gameToCartItem = new GameToCartItem();
		gameToCartItem.setGame(game);
		gameToCartItem.setCartItem(cartItem);
		gameToCartItemRepository.save(gameToCartItem);
		
		return cartItem;
	}
	
	public CartItem findById(Long id) {
		return cartItemRepository.findById(id).orElse(null);
	}
	
	public void removeCartItem(CartItem cartItem) {
		gameToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}
	
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	
	public List<CartItem> findByOrder(Order order) {
		return cartItemRepository.findByOrder(order);
	}
}
