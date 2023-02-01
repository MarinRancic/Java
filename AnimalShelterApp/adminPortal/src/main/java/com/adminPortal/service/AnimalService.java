package com.adminPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.adminPortal.domain.Animal;

@Service
public interface AnimalService {
	Animal save(Animal animal);
	
	List<Animal> findAll();
	
	Optional<Animal> findById(Long id);
	
	void removeById(Long id);
	
	Page<Animal> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection);
}
