package com.adminPortal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String company;
	
	private String category;
	
	private String platform;
	
	private double size;
	
	private double listPrice;
	
	private double ourPrice;
	
	@Column(columnDefinition="text")
	private String description;
	
	@OneToMany(mappedBy="game")
	@JsonIgnore
	private List<GameToCartItem> gameToCartItem;
	private int inStockNumber;
	
	@Transient
	private MultipartFile gameImage;
}
