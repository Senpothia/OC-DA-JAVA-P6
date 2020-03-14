package com.formation.escalade;

import java.security.Principal;
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
import com.formation.escalade.model.Profil;
import com.formation.escalade.model.Topo;
import com.formation.escalade.model.User;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.repository.IProfil;
import com.formation.escalade.repository.IUtilisateur;

@Controller
public class HomeController {
	
	private final IUtilisateur utilisateurRepo;
	private final IProfil profilRepo;
	
	public HomeController( IUtilisateur utilisateurRepo, IProfil profilRepo){
	
		this.utilisateurRepo = utilisateurRepo;
		this.profilRepo = profilRepo;
	}
	
	@GetMapping("/")
	public String accueil(Model model, HttpSession session,HttpServletRequest request, Principal principal ) {
		
		System.out.println("entrée accueil()");

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		
		return "index";
	}
	
	@GetMapping("/presentation")
	public String presentation(HttpSession session,HttpServletRequest request, Principal principal, Model model) {
		
		System.out.println("entrée presentation()");

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		return "presentation";
	}
	
	@GetMapping("/connexion")
	public String connexion(@RequestParam(name="error", required=false) boolean error, Model model) {
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
