package com.formation.escalade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.User;

@Controller
public class HomeController {
	
	private final IUtilisateur utilisateurRepo;
	
	public HomeController( IUtilisateur utilisateurRepo){
	
		this.utilisateurRepo = utilisateurRepo;
	}
	
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
		User user = new User();
		model.addAttribute("user",user );
		
		return "connexion";
	}
	
	
	@PostMapping("/connexion")
	public String getCompte(User user) {
		
		System.out.println(user.toString());
		Utilisateur utilisateur = utilisateurRepo.findByEmail();
		String passe = null;
		
		if (utilisateur != null){
			
			String passe = utilisateur.getPasse();
			
			if (passe != null){
			
				system.out.println("Connexion réussie");
			}
		}
		
		return "ok";
	}
	

}
