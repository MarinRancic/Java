package com.adminPortal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.adminPortal.domain.User;
import com.adminPortal.domain.security.Role;
import com.adminPortal.domain.security.UserRole;
import com.adminPortal.service.UserService;
import com.adminPortal.utility.SecurityUtility;



@SpringBootApplication
public class AdminPortalApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(AdminPortalApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		var user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("123"));
		Set<UserRole> userRoles = new HashSet<>();
		var role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1,role1));
		
		userService.createUser(user1, userRoles);
	}
}
