package com.gameshop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class BillingAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String BillingAddressName;
	
	private String BillingAddressStreet1;
	
	private String BillingAddressStreet2;
	
	private String BillingAddressCity;
	
	private String BillingAddressState;
	
	private String BillingAddressCountry;
	
	private String BillingAddressZipcode;
	
	@OneToOne
	private Order order;
}
