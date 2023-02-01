package com.gameshop.domain;

import javax.persistence.CascadeType;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userBillingName;
	
	private String userBillingStreet1;
	
	private String userBillingStreet2;
	
	private String userBillingCity;
	
	private String userBillingState;
	
	private String userBillingCountry;
	
	private String userBillingZipcode;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserPayment userPayment;
}