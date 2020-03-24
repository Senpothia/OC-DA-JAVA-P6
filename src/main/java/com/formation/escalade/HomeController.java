package com.formation.escalade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
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
		
	}
	
	@PostMapping("/connexion")
	public String getCompte(User user, HttpSession session,HttpServletRequest request, Model model ) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		
		return "redirect:espace";
	}
	
	// Méthodes de test  - @Query sur la table utilisateur
	
	
	@GetMapping("/user")
	public String user() {
		
		Collection<Utilisateur>  users1 = new ArrayList<Utilisateur>();
		//users1 = utilisateurRepo.findAllActiveUsers();
		users1 = utilisateurRepo.findAllMembres();
		
		System.out.println("taille de la liste des utilisateurs membre: " + users1.size());
		
		Collection<Utilisateur>  users2 = new ArrayList<Utilisateur>();
		//users2 = utilisateurRepo.findAllInactiveUsers();
		users2 = utilisateurRepo.findAllUtilisateurs();
		System.out.println("taille de la liste des utilisateurs non membre: " + users2.size());
		
		
		Collection<Utilisateur>  users3 = new ArrayList<Utilisateur>();
		//users2 = utilisateurRepo.findAllInactiveUsers();
		users3 = utilisateurRepo.findAllMembresNative();
		System.out.println("taille de la liste des utilisateurs membre (native): " + users3.size());
		
		return "ok";
	}

}
