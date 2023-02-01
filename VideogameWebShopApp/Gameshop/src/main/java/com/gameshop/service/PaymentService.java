package com.gameshop.service;

import com.gameshop.domain.Payment;
import com.gameshop.domain.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
