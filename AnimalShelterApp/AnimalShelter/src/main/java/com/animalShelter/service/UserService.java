package com.animalShelter.service;

import java.util.Set;

import com.animalShelter.domain.User;
import com.animalShelter.domain.security.UserRole;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);
	
	User save(User user);
	
	User findByUsername(String username);
}
