package com.adminPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adminPortal.domain.Animal;
import com.adminPortal.domain.Commentary;
import com.adminPortal.service.AnimalService;
import com.adminPortal.service.CommentaryService;

@Controller
public class HomeController {

	@Autowired
	private AnimalService animalService;

	@Autowired
	private CommentaryService commentaryService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		var animalList = animalService.findAll();
		model.addAttribute("animalList", animalList);

		return findPaginated(1, "name", "asc",  model);
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		var page = animalService.findPaginated(pageNumber, pageSize, sortField, sortDir);
		var animalList = page.getContent();

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("animalList", animalList);
		return "home";
	}
	
	@GetMapping("/commentaryList")
	public String commentaryList(Model model) {
		var commentaryList = commentaryService.findAll();
		model.addAttribute("commentaryList", commentaryList);
		return "commentaryList";
	}
	
	@GetMapping("/commentaryList/remove")
	public String remove(@ModelAttribute("id") Long id) {
		commentaryService.removeById(id);
		return "redirect:/commentaryList";
	}
	
	@GetMapping("/commentaryList/changeActive")
	public String changeActive(@ModelAttribute("id") Long id) {
		var commentary = commentaryService.findById(id);
		if (commentary.isPresent()) {
			if (commentary.get().isActive()) {
				commentary.get().setActive(false);
			}
			else {
				commentary.get().setActive(true);
			}
			commentaryService.save(commentary.get());
		}
		
		return "redirect:/commentaryList";
	}
	
	@PostMapping("/commentaryList/newResponse")
	public String newResponse(@ModelAttribute("id") Long id, @ModelAttribute("adminResponse") String adminResponse) {
		var commentary = commentaryService.findById(id);
		if (commentary.isPresent()) {
			commentary.get().setAdminResponse(adminResponse);
			commentaryService.save(commentary.get());
		}
		return "redirect:/commentaryList";
	}
}