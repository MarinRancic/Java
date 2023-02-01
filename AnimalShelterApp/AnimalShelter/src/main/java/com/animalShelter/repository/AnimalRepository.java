package com.animalShelter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animalShelter.domain.Animal;


public interface AnimalRepository extends JpaRepository<Animal, Long> {
	List<Animal> findBySpecies(String species);
}
