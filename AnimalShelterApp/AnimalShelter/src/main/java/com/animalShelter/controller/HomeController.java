package com.animalShelter.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.animalShelter.domain.Animal;
import com.animalShelter.domain.Commentary;
import com.animalShelter.domain.User;
import com.animalShelter.domain.security.Role;
import com.animalShelter.domain.security.UserRole;
import com.animalShelter.service.AnimalService;
import com.animalShelter.service.CommentaryService;
import com.animalShelter.service.UserService;
import com.animalShelter.utility.SecurityUtility;

@Controller
public class HomeController {

	@Autowired
	AnimalService animalService;

	@Autowired
	UserService userService;

	@Autowired
	CommentaryService commentaryService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}

	@GetMapping("/adopt")
	public String adopt(Model model) {
		var animalList = animalService.findAll();
		model.addAttribute("animalList", animalList);
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/volunteer")
	public String volunteer() {
		return "volunteer";
	}

	@GetMapping("/contactUs")
	public String contactUs(Model model) {
		var commentaryList = commentaryService.findAll();
		model.addAttribute("commentaryList", commentaryList);
		return "contactUs";
	}

	@GetMapping("/donate")
	public String donate() {
		return "donate";
	}

	@GetMapping("/animal/animalInfo")
	public String animalInfo(@ModelAttribute("id") Long id, Model model) {
		var animal = animalService.findById(id);
		if (animal.isPresent()) {
			model.addAttribute("animal", animal.get());
		}
		return "animalInfo";
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
		return "adopt";
	}

	@GetMapping("/searchBySpecies")
	public String searchBySpecies(@RequestParam("species") String species, Model model) {

		var animalList = animalService.findBySpecies(species);

		model.addAttribute("animalList", animalList);
		return "adopt";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/newUser")
	public String newUser(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
			Model model) {

		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "/registration";
		}

		var user = new User();
		user.setUsername(username);
		user.setPassword(SecurityUtility.passwordEncoder().encode(password));
		var role = new Role();
		role.setRoleId(2);
		role.setName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));

		userService.createUser(user, userRoles);
		model.addAttribute("accountCreated", true);
		return "/registration";
	}

	@PostMapping("/newComment")
	public String newComment(@ModelAttribute("commentary") String text, @ModelAttribute("username") String username,
			Model model) {
		var commentary = new Commentary();
		commentary.setText(text);
		commentary.setUsername(username);
		commentary.setActive(false);
		model.addAttribute("commentSent", true);
		commentaryService.createCommentary(commentary);
		return "redirect:/contactUs";
	}

}
