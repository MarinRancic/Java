package com.adminPortal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminPortal.domain.Commentary;
import com.adminPortal.repository.CommentaryRepository;
import com.adminPortal.service.CommentaryService;

@Service
public class CommentaryServiceImpl implements CommentaryService {

	@Autowired
	private CommentaryRepository commentaryRepository;
	
	public List<Commentary> findAll() {
		return (List<Commentary>) commentaryRepository.findAll();
	}
	
	public void removeById(Long id) {
		commentaryRepository.deleteById(id);
	}
	
	public Optional<Commentary> findById(Long id) {
		return commentaryRepository.findById(id);
	}
	public Commentary save(Commentary commentary) {
		return commentaryRepository.save(commentary);
	}
}
