package com.formation.escalade;

import java.lang.module.FindException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.repository.IUtilisateur;
import com.sun.xml.bind.v2.model.core.ID;

@Controller
public class CompteController {
	
	private final IUtilisateur utilisateurRepo;
	
	public CompteController(IUtilisateur utilisateurRepo){
		
		this.utilisateurRepo = utilisateurRepo;
	}
	
	@GetMapping("/compte")
	public String compte(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "compte";
	}
	
	@PostMapping("/compte")
	public String compteSubmit(@ModelAttribute Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", utilisateur);
		System.out.println(utilisateur.toString());
		
		utilisateur.setMembre(false);
		
		utilisateurRepo.save(utilisateur);
		return "accueil";
	}
	
	@GetMapping("/modificationCompte")
	public String modificationCompte(Utilisateur utilisateur) {
		
		return "modificationCompte";
	}
	
	@PostMapping("/modificationCompte")
	public String compteModification(@ModelAttribute Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", utilisateur);
		System.out.println(utilisateur.toString());
		
		utilisateur.setMembre(false);
		
		utilisateurRepo.save(utilisateur);
		return "accueil";
	}
	
	@GetMapping("/espace")
	public String espace(Model model) {
		model.addAttribute("utilisateur", new Utilisateur(100, "Hugo", "Victor", 75, "mail@hhg", "passvictor",false));
		return "espace";
	}
	
	@PostMapping("/espace")
	public String espaceSubmit(@ModelAttribute Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", new Utilisateur(100, "Hugo", "Victor", 75, "mail@hhg", "passvictor",false));
		System.out.println(utilisateur.toString());
		return "espace";
	}
	
	@GetMapping("/suppressionCompte/{id}") 
		
		public String supprimerCompte(@PathVariable("id") int id,Model model) {
			
			model.addAttribute("id",id);
			utilisateurRepo.delete(utilisateurRepo.getOne(id));
		       System.out.println("Utilisateur id: " + id +"Supprimé");
		       return "test";
		   }
   
		
	}


