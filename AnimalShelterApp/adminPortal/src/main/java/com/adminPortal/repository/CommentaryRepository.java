package com.adminPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminPortal.domain.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long>{

}
