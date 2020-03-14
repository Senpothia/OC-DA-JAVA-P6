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
	public String accueil(Model model, HttpSession session,HttpServletRequest request ) {
		
		/*
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
		*/
		Utilisateur user1 = utilisateurRepo.getOne(15);
		List <Profil> profils1 = user1.getProfils();
		if (profils1 == null) {
			
			Profil p1 = profilRepo.findByRole("MEMBRE");
			Profil p2 = profilRepo.findByRole("USER");
			profils1.add(p1);
			profils1.add(p2);
			user1.setProfils(profils1);
			utilisateurRepo.save(user1);
		}
		
		
		Utilisateur user2 = utilisateurRepo.getOne(8);
		List <Profil> profils2 = user2.getProfils();
		System.out.println("Nom user2: " + user2.getNom());
		if (profils2 == null) {
			
			//Profil p3 = profilRepo.findByRole("MEMBRE");
			Profil p4 = profilRepo.findByRole("USER");
			//profil2.add(p3);
			profils2.add(p4);
			user2.setProfils(profils2);
			utilisateurRepo.save(user2);
		}
		
		//return "ok";*/
		return "index";
	}
	
	@GetMapping("/presentation")
	public String presentation() {
		
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
