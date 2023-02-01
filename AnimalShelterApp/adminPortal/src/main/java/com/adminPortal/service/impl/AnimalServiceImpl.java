package com.adminPortal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.adminPortal.domain.Animal;
import com.adminPortal.repository.AnimalRepository;
import com.adminPortal.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	public Animal save(Animal animal) {
		return animalRepository.save(animal);
	}
	
	public List<Animal> findAll(){
		return (List<Animal>) animalRepository.findAll();
	}
	
	public Optional<Animal> findById(Long id) {
		return animalRepository.findById(id);
	}
	
	public void removeById(Long id) {
		animalRepository.deleteById(id);
	}

	public Page<Animal> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
		var sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		var pageable = PageRequest.of(pageNumber -1, pageSize, sort);
		return animalRepository.findAll(pageable);
	}
}
