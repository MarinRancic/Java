package com.animalShelter.repository;

import org.springframework.data.repository.CrudRepository;

import com.animalShelter.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
}
