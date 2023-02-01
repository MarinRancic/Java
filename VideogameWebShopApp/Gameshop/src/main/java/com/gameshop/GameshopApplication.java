package com.gameshop;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gameshop.domain.User;
import com.gameshop.domain.security.Role;
import com.gameshop.domain.security.UserRole;
import com.gameshop.service.UserService;
import com.gameshop.utility.SecurityUtility;

@SpringBootApplication
public class GameshopApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(GameshopApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		User user1 = new User();
		user1.setFirstName("admin");
		user1.setLastName("admin");
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("123"));
		user1.setEmail("admin@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1,role1));
		
		userService.createUser(user1, userRoles);
	}

}
