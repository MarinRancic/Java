package com.gameshop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameshop.domain.CartItem;
import com.gameshop.domain.Game;
import com.gameshop.domain.ShoppingCart;
import com.gameshop.domain.User;
import com.gameshop.service.CartItemService;
import com.gameshop.service.GameService;
import com.gameshop.service.ShoppingCartService;
import com.gameshop.service.UserService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}	
	
	@RequestMapping("/addItem")
	public String addItem(@ModelAttribute("game") Game game, @ModelAttribute("qty") String qty, Model model, Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		game = gameService.findById(game.getId());
		
		if (Integer.parseInt(qty) > game.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/gameDetails?id="+game.getId();
		}
		
		CartItem cartItem = cartItemService.addGameToCartItem(game, user, Integer.parseInt(qty));
		model.addAttribute("addGameSuccess", true);
		
		return "forward:/gameDetails?id="+game.getId();
	}
	
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(@ModelAttribute("id") Long cartItemId, @ModelAttribute("qty") int qty) {
		
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
}
