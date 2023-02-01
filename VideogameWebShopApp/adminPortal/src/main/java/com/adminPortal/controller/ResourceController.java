package com.adminPortal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminPortal.service.GameService;

@RestController
public class ResourceController {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value="/game/removeList", method=RequestMethod.POST)
	public String removeList(@RequestBody ArrayList<String> gameIdList, Model model) {
		for (String id: gameIdList) {
			String gameId = id.substring(8);
			gameService.removeById(Long.parseLong(gameId));
		}
		
		return "delete success";
	}
}
