package com.animalShelter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animalShelter.domain.Commentary;
import com.animalShelter.repository.CommentaryRepository;
import com.animalShelter.service.CommentaryService;

@Service
public class CommentaryServiceImpl implements CommentaryService{
	
	@Autowired
	private CommentaryRepository commentaryRepository;
	
	public Commentary createCommentary(Commentary commentary) {
		return commentaryRepository.save(commentary);
	}
	
	public List<Commentary> findAll() {
		return (List<Commentary>) commentaryRepository.findAll();
	}
}
