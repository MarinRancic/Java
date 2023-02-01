package com.animalShelter.repository;

import org.springframework.data.repository.CrudRepository;

import com.animalShelter.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
