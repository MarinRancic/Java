package com.adminPortal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminPortal.domain.Game;
import com.adminPortal.repository.GameRepository;
import com.adminPortal.service.GameService;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public Game save(Game game) {
		return gameRepository.save(game);
	}
	
	public List<Game> findAll(){
		return (List<Game>) gameRepository.findAll();
	}
	
	public Game findById(Long id) {
		return gameRepository.findById(id).orElse(null);
	}
	
	public void removeById(Long id) {
		gameRepository.deleteById(id);
	}
}
