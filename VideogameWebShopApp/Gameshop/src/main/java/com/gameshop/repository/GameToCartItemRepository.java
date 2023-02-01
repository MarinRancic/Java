package com.gameshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gameshop.domain.CartItem;
import com.gameshop.domain.GameToCartItem;

@Transactional
public interface GameToCartItemRepository extends CrudRepository<GameToCartItem, Long>{
	void deleteByCartItem(CartItem cartItem);
}
