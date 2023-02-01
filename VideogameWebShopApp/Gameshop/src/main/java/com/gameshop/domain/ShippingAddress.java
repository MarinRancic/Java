package com.gameshop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShippingAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String ShippingAddressName;
	
	private String ShippingAddressStreet1;
	
	private String ShippingAddressStreet2;
	
	private String ShippingAddressCity;
	
	private String ShippingAddressState;
	
	private String ShippingAddressCountry;
	
	private String ShippingAddressZipcode;
	
	@OneToOne
	private Order order;
}
