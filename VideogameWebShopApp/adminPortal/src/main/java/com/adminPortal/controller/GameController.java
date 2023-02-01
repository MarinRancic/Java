package com.adminPortal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminPortal.domain.Game;
import com.adminPortal.service.GameService;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addGame(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);
		return "addGame";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addGamePost(@ModelAttribute("game") Game game, HttpServletRequest request) {
		gameService.save(game);
		
		MultipartFile gameImage = game.getGameImage();
		
		try  {
			byte[] bytes = gameImage.getBytes();
			String name = game.getId()+".png";
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/games/"+name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:gameList";
	}
	
	@RequestMapping("/gameList")
	public String gameList(Model model) {
		List<Game> gameList = gameService.findAll();
		model.addAttribute("gameList", gameList);
		return "gameList";
	}
	
	@RequestMapping("/gameInfo")
	public String gameInfo(@RequestParam("id") Long id, Model model) {
		Game game = gameService.findById(id);
		model.addAttribute("game", game);
		return "gameInfo";
	}
	
	@RequestMapping("/updateGame")
	public String updateGame(@RequestParam("id") Long id, Model model) {
		Game game = gameService.findById(id);
		model.addAttribute("game", game);
		return "updateGame";
	}
	
	@RequestMapping(value="/updateGame", method=RequestMethod.POST)
	public String updateGamePost(@ModelAttribute("game") Game game, HttpServletRequest request) {
		gameService.save(game);
		MultipartFile gameImage = game.getGameImage();
		if (!gameImage.isEmpty()) {
			try  {
				byte[] bytes = gameImage.getBytes();
				String name = game.getId()+".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/games/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/games/"+name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/game/gameInfo?id="+game.getId();
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@ModelAttribute("id") String id, Model model) {
		gameService.removeById(Long.parseLong(id.substring(8)));
		List<Game> gameList = gameService.findAll();
		model.addAttribute("gameList", gameList);
		
		return "redirect:/game/gameList";
	}
}
