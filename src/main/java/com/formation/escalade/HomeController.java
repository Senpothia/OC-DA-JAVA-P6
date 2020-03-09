package com.formation.escalade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.Demande;
import com.formation.escalade.model.Topo;
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
	public String accueil(Model model, HttpSession session,HttpServletRequest request ) {
		
		
		Boolean authentification=true;  // provisoire
		//boolean authentification = (boolean) request.getSession().getAttribute("AUTH");
		
		session.setAttribute("AUTH", authentification);
		model.addAttribute("authentification", authentification);
		if (authentification) {
			
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("Hugo");
		utilisateur.setPrenom("Victor");
		utilisateur.setDepartement(78);
		utilisateur.setEmail("victor@gmail.com");
		utilisateur.setPrenom("passevictor");
		utilisateur.setMembre(true);
		model.addAttribute("utilisateur", utilisateur);
		
		}
		//return "ok";*/
		return "index";
	}
	
	@GetMapping("/presentation")
	public String presentation() {
		
		return "presentation";
	}
	
	@GetMapping("/connexion")
	public String connexion(@RequestParam (name= "error", required=false) boolean error, Model model) {
		User user = new User();
		model.addAttribute("user",user );
		model.addAttribute("error", error);
		
		return "connexion";
		//return "connexion2"; // Version initiale
	}
	
	
	
	
	
	@PostMapping("/connexion")
	public String getCompte(User user, HttpSession session,HttpServletRequest request ) {
		/**
		boolean authentification = (boolean) request.getSession().getAttribute("AUTH");
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
				authentification = true;
			
			} else {
				System.out.println("Connexion échouée!");
				
			}
		session.setAttribute("AUTH", authentification);
		*/
		return "redirect:espace";
	}
	

}
