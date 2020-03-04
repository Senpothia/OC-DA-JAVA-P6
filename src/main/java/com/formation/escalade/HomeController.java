package com.formation.escalade;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.User;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.repository.IUtilisateur;

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
	public String getCompte(User user, HttpSession session ) {
		
		System.out.println(user.toString());
		Utilisateur utilisateur = utilisateurRepo.findByEmail(user.getEmail());
		
		String email = utilisateur.getEmail();
		String passe = utilisateur.getPasse();
		String usermail = user.getEmail();
		String userpassword = user.getPassword();
		
		System.out.println("email en base: " + email);
		System.out.println("passe en base: " + passe);
		System.out.println("email  du formulaire: " + user.getEmail());
		System.out.println("password  du formulaire: " + user.getPassword());
		
		if (email.equals(usermail) && passe.equals(userpassword)){
			
				System.out.println("Connexion réussie!");
				session.setAttribute("USER", utilisateur);
			
			} else {
				System.out.println("Connexion échouée!");
				
			}
		
		return "ok";
	}
	

}
