package com.adminPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adminPortal.domain.Commentary;

@Service
public interface CommentaryService {
	List<Commentary> findAll();
	
	void removeById(Long id);
	
	Optional<Commentary> findById(Long id);

	Commentary save(Commentary commentary);
}
