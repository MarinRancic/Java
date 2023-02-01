package com.adminPortal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminPortal.domain.Animal;
import com.adminPortal.service.AnimalService;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@GetMapping("/add")
	public String addAnimal(Model model) {
		var animal = new Animal();
		model.addAttribute("animal", animal);
		return "addAnimal";
	}

	@PostMapping("/add")
	public String addAnimalPost(@ModelAttribute("animal") Animal animal, HttpServletRequest request) {

		animalService.save(animal);

		var animalImage = animal.getAnimalImage();
		try {
			byte[] bytes = animalImage.getBytes();
			String name = animal.getId() + ".png";
			var stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/animals/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/home";
	}

	@GetMapping("/animalInfo")
	public String animalInfo(@ModelAttribute("id") Long id, Model model) {
		var animal = animalService.findById(id);
		if (animal.isPresent()) {
			model.addAttribute("animal", animal.get());
		}
		return "animalInfo";

	}

	@GetMapping("/updateAnimal")
	public String updateAnimal(@RequestParam("id") Long id, Model model) {
		var animal = animalService.findById(id);
		if (animal.isPresent()) {
			model.addAttribute("animal", animal.get());
		}
		return "updateAnimal";
	}

	@PostMapping("/updateAnimal")
	public String updateAnimalPost(@ModelAttribute("animal") Animal animal, HttpServletRequest request) {
		animalService.save(animal);
		var animalImage = animal.getAnimalImage();
		if (!animalImage.isEmpty()) {
			try {
				byte[] bytes = animalImage.getBytes();
				String name = animal.getId() + ".png";

				Files.delete(Paths.get("src/main/resources/static/image/animals/" + name));

				var stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/animals/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/home";
	}

	@GetMapping("/remove")
	public String remove(@ModelAttribute("id") Long id) {
		animalService.removeById(id);
		return "redirect:/home";
	}

}
