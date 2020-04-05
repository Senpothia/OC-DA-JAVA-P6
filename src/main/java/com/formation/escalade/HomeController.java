package com.formation.escalade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import ch.qos.logback.classic.pattern.Util;

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
		
		model.addAttribute("phrase", new String());
		return "index";
	}
	
	@GetMapping("/presentation")
	public String presentation(HttpSession session,HttpServletRequest request, Principal principal
			, Model model) {
		
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
		model.addAttribute("phrase", new String());
		return "presentation";
	}
	
	@GetMapping("/connexion")
	public String connexion(@RequestParam(name="error", required=false) boolean error, Model model) {
		User user = new User();
		model.addAttribute("user",user );
		model.addAttribute("error", error);
		model.addAttribute("phrase", new String());
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
		
		model.addAttribute("phrase", new String());
		return "redirect:espace";
	}
	
	// Méthodes de test  - @Query sur la table utilisateur
	
	
	@GetMapping("/user")
	public String user(Model model) {
		
		Collection<Utilisateur>  users1 = new ArrayList<Utilisateur>();
		//users1 = utilisateurRepo.findAllActiveUsers();
		users1 = utilisateurRepo.findAllMembres();
		
		System.out.println("taille de la liste 1 des utilisateurs membre: " + users1.size());
		
		Collection<Utilisateur>  users2 = new ArrayList<Utilisateur>();
		//users2 = utilisateurRepo.findAllInactiveUsers();
		users2 = utilisateurRepo.findAllUtilisateurs();
		System.out.println("taille de la liste 2 des utilisateurs non membre: " + users2.size());
		
		
		Collection<Utilisateur>  users3 = new ArrayList<Utilisateur>();
		//users2 = utilisateurRepo.findAllInactiveUsers();
		users3 = utilisateurRepo.findAllMembresNative();
		System.out.println("taille de la liste 3 des utilisateurs membre (native): " + users3.size());
		model.addAttribute("phrase", new String());
		
		
		//List<Utilisateur> users4 = utilisateurRepo.findByNomLike("Rig");
		//System.out.println("taille de la liste 4 des utilisateurs membre recherche like: " + users4.size());
		
		
		List<Utilisateur> users5 = utilisateurRepo.findByNomIgnoreCase("lopez");
		System.out.println("taille liste 5 sans prise en compte casse: " + users5.size());
		
		List<Utilisateur> users6 = utilisateurRepo.findAllRs();
		System.out.println("taille liste 6 all L: " + users6.size());
		
		//Collection<Utilisateur> users7 = utilisateurRepo.findByFirstnameEndsWith("z");
		//System.out.println("taille liste 7 end by z: " + users6.size());
		
		Utilisateur users8 = utilisateurRepo.findByEmailAddress("michel@gmail.com");
	    System.out.println("taille liste 8 par mail native: " + users8.getNom());
		
		List<Utilisateur> users9 = utilisateurRepo.findAllStartBy("Lop%");
		System.out.println("taille liste 9 start by...: " + users9.size());
		System.out.println("Liste 9, nom user9: " + users9.get(0).getNom());
	    
	    List<Utilisateur> users10 = utilisateurRepo.findByNomIgnoreCase("lopez");
	    System.out.println("taille liste 10 ignore case: " + users10.size());
	    System.out.println("Liste 10, nom user10: " + users10.get(0).getNom());
	    
		return "ok";
	}

}
