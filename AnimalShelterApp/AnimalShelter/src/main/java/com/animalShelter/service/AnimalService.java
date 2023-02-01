package com.animalShelter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.animalShelter.domain.Animal;


@Service
public interface AnimalService {
	
	List<Animal> findAll();
	
	Optional<Animal> findById(Long id);
	
	Page<Animal> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
	
	List<Animal> findBySpecies(String species);
}
