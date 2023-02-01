package com.gameshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.gameshop.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
}
