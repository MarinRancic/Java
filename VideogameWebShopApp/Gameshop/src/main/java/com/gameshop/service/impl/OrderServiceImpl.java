package com.gameshop.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.domain.BillingAddress;
import com.gameshop.domain.CartItem;
import com.gameshop.domain.Game;
import com.gameshop.domain.Order;
import com.gameshop.domain.Payment;
import com.gameshop.domain.ShippingAddress;
import com.gameshop.domain.ShoppingCart;
import com.gameshop.domain.User;
import com.gameshop.repository.OrderRepository;
import com.gameshop.service.CartItemService;
import com.gameshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Game game = cartItem.getGame();
			cartItem.setOrder(order);
			game.setInStockNumber(game.getInStockNumber() - cartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}

	public Order findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}
}
