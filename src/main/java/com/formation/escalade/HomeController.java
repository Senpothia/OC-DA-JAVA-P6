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
	

}
