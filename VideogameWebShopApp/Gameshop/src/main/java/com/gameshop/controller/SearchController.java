package com.gameshop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameshop.domain.Game;
import com.gameshop.domain.User;
import com.gameshop.service.GameService;
import com.gameshop.service.UserService;

@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping("/searchByCategory")
	public String searchByCategory(@RequestParam("category") String category, Model model, Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user",user);
			
		}
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Game> gameList = gameService.findByCategory(category);
		
		if (gameList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "browseGames";
		}
		
		model.addAttribute("gameList", gameList);
		
		return "browseGames";
	}
	
	@RequestMapping("/searchGame")
	public String searchGame(@ModelAttribute("keyword") String keyword, Principal principal, Model model) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		List<Game> gameList = gameService.blurrySearch(keyword);
		
		if(gameList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "browseGames";
		}
		
		model.addAttribute("gameList", gameList);
		
		return "browseGames";
	}
	
	
	
	
	
	
}
