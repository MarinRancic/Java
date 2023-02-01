package com.gameshop.service;

import java.util.List;

import com.gameshop.domain.Game;
import com.gameshop.repository.GameRepository;

public interface GameService {
	List<Game> findAll();
	
	Game findById(Long id);
	
	List<Game> findByCategory(String category);
	
	List<Game> blurrySearch(String title);
}
