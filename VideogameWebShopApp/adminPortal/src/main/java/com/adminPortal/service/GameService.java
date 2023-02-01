package com.adminPortal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adminPortal.domain.Game;


@Service
public interface GameService {
	Game save(Game game);
	
	List<Game> findAll();
	
	Game findById(Long id);
	
	void removeById(Long id);
}
