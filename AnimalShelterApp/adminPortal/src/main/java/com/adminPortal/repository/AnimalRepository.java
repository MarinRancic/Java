package com.adminPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminPortal.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
}
