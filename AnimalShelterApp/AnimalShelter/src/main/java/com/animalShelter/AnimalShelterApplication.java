package com.animalShelter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.animalShelter.domain.User;
import com.animalShelter.domain.security.Role;
import com.animalShelter.domain.security.UserRole;
import com.animalShelter.service.UserService;
import com.animalShelter.utility.SecurityUtility;

@SpringBootApplication
public class AnimalShelterApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args){
		SpringApplication.run(AnimalShelterApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		var user2 = new User();
		user2.setUsername("user");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("123"));
		Set<UserRole> userRoles = new HashSet<>();
		var role2 = new Role();
		role2.setRoleId(2);
		role2.setName("ROLE_USER");
		userRoles.add(new UserRole(user2,role2));
		
		userService.createUser(user2, userRoles);
	}
}
