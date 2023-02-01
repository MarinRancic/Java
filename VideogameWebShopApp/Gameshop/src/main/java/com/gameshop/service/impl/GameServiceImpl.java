package com.gameshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.domain.Game;
import com.gameshop.repository.GameRepository;
import com.gameshop.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> findAll(){
		return (List<Game>) gameRepository.findAll();
	}
	
	public Game findById(Long id) {
		return gameRepository.findById(id).orElse(null);
	}
	
	public List<Game> findByCategory(String category) {
		return gameRepository.findByCategory(category);
	}
	
	public List<Game> blurrySearch(String title) {
		return gameRepository.findByTitleContaining(title);
	}
}
