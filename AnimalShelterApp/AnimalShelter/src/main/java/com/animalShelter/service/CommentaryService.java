package com.animalShelter.service;

import java.util.List;

import com.animalShelter.domain.Commentary;

public interface CommentaryService {
	Commentary createCommentary(Commentary commentary);
	
	List<Commentary> findAll();
}
