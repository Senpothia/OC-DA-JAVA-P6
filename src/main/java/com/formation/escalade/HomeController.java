package com.formation.escalade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String connexion() {
		
		return "connexion";
	}
	
	@GetMapping("/galerie")
	public String galerie() {
		
		return "galerie";
	}
	
	
	

}