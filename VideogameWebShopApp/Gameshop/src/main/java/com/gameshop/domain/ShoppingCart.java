package com.gameshop.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private BigDecimal grandTotal;
	
	@OneToMany(mappedBy="shoppingCart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CartItem> cartItemList;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
}
