package com.formation.escalade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.User;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String accueil() {
		
		return "index";
	}
	
	@GetMapping("/presentation")
	public String presentation() {
		
		return "presentation";
	}
	
	@GetMapping("/connexion")
	public String connexion(Model model) {
		
		model.addAttribute("user", new User());
		
		return "connexion";
	}
	
	
	@PostMapping("/connexion")
	public String getCompte() {
		
		
		return "ok";
	}
	
	
	

}