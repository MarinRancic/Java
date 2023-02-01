package com.adminPortal.service;

import java.util.Set;

import com.adminPortal.domain.User;
import com.adminPortal.domain.security.UserRole;



public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
