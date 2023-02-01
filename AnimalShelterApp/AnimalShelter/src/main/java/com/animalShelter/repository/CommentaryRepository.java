package com.animalShelter.repository;

import org.springframework.data.repository.CrudRepository;

import com.animalShelter.domain.Commentary;

public interface CommentaryRepository extends CrudRepository<Commentary, Long> {

}
