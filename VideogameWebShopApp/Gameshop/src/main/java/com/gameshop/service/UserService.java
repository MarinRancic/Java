package com.gameshop.service;

import java.util.Set;

import com.gameshop.domain.User;
import com.gameshop.domain.UserBilling;
import com.gameshop.domain.UserPayment;
import com.gameshop.domain.UserShipping;
import com.gameshop.domain.security.PasswordResetToken;
import com.gameshop.domain.security.UserRole;

public interface UserService {
	
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findById(Long id);
	
	User findByEmail (String email);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void updateUserShipping(UserShipping userShipping, User user);
	
	void setUserDefaultPayment(Long userPaymentId, User user);
	
	void setUserDefaultShipping(Long userShippingId, User user);
}
