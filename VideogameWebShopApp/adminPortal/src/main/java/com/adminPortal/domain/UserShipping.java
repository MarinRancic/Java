package com.adminPortal.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserShipping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userShippingName;
	
	private String userShippingStreet1;
	
	private String userShippingStreet2;
	
	private String userShippingCity;
	
	private String userShippingState;
	
	private String userShippingCountry;
	
	private String userShippingZipcode;
	
	private boolean userShippingDefault;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
