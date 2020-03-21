package com.formation.escalade;

import java.lang.module.FindException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.FormCompte;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.repository.IUtilisateur;
import com.sun.xml.bind.v2.model.core.ID;

@Controller
public class CompteController {

	private final IUtilisateur utilisateurRepo;
	private final PasswordEncoder passwordEncoder;

	public CompteController(IUtilisateur utilisateurRepo, PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder = passwordEncoder;
		this.utilisateurRepo = utilisateurRepo;
	}

	@GetMapping("/compte")   // Création de compte
	public String compte(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "compte";
	}

	@PostMapping("/compte")	// Création de compte
	public String compteSubmit(@ModelAttribute Utilisateur utilisateur, Model model) {
		
		
		List<Utilisateur> users = utilisateurRepo.findAll();
		if (users.size() == 0) {
			
			utilisateur.setMembre(true);
			System.out.println("Premier utilisateur créée: membre d'office");
			
		} else {
			
			utilisateur.setMembre(false);
			System.out.println("Création membre ordinaire");
		}
		
		utilisateur.setPasse(passwordEncoder.encode(utilisateur.getPasse()));

		utilisateurRepo.save(utilisateur);
		return "accueil";
	}

	

	@GetMapping("/espace")    // Accès espace personnel
	public String espace( Model model, HttpServletRequest request, Principal principal) {
		String email = request.getUserPrincipal().getName();
		model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
		return "espace";
	}
	
	
	@GetMapping("/Compte/supprimer/{id}")

	public String supprimerCompte(@PathVariable("id") int id, Model model) {

		model.addAttribute("id", id);
		utilisateurRepo.delete(utilisateurRepo.getOne(id));
		System.out.println("Utilisateur id: " + id + "Supprimé");
		return "test";
	}

	@PostMapping("/compte/supprimer")

	public String deleteAccount(@ModelAttribute Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", utilisateur);
		utilisateurRepo.delete(utilisateur);
		return "test";
	}

	@GetMapping("/compte/modifier")
	public String modificationCompte(Utilisateur utilisateur, 
			Model model, HttpServletRequest request) {
		
		try {
	
			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);
	
		} catch (NullPointerException e) {
	
			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		String email = request.getUserPrincipal().getName();
		utilisateur = utilisateurRepo.findByEmail(email);
		FormCompte formCompte = new FormCompte();
		formCompte.setPrenom(utilisateur.getPrenom());
		formCompte.setNom(utilisateur.getNom());
		formCompte.setDepartement(utilisateur.getDepartement());
		formCompte.setEmail(utilisateur.getEmail());
		
		model.addAttribute("formCompte", formCompte);	
		
		return "modificationCompte";
	}

	@PostMapping("/compte/modifier")
	public String compteModification(@ModelAttribute Utilisateur utilisateur
			, Model model, FormCompte formCompte, HttpServletRequest request) {
		
		try {
			
			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);
	
		} catch (NullPointerException e) {
	
			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		
		
		String email = request.getUserPrincipal().getName();
		utilisateur = utilisateurRepo.findByEmail(email);
		
		utilisateur.setPrenom(formCompte.getPrenom());
		utilisateur.setNom(formCompte.getNom());
		utilisateur.setDepartement(formCompte.getDepartement());
		utilisateur.setEmail(formCompte.getEmail());

		utilisateur.setPasse(passwordEncoder.encode(formCompte.getPassword()));
		utilisateurRepo.save(utilisateur);
		
		return "espace";
	}

}
