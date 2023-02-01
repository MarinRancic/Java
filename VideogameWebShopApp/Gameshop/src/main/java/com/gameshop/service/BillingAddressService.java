package com.gameshop.service;

import com.gameshop.domain.BillingAddress;
import com.gameshop.domain.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
