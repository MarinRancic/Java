package com.adminPortal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminPortal.domain.Game;


public interface GameRepository extends CrudRepository<Game, Long> {
	
}
