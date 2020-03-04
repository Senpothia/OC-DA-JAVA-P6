package com.formation.escalade;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort;
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

	public CompteController(IUtilisateur utilisateurRepo) {

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
	public String espace( Model model, HttpServletRequest request) {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("USER");
		model.addAttribute("utilisateur", utilisateur);
		return "espace";
	}
/**
	@PostMapping("/espace")
	public String espaceSubmit( Model model, HttpServletRequest request) {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("USER");
		model.addAttribute("utilisateur", utilisateur);
		System.out.println(utilisateur.toString());
		return "espace";
	}
*/
	@GetMapping("/suppressionCompte/{id}")

	public String supprimerCompte(@PathVariable("id") int id, Model model) {

		model.addAttribute("id", id);
		utilisateurRepo.delete(utilisateurRepo.getOne(id));
		System.out.println("Utilisateur id: " + id + "Supprimé");
		return "test";
	}

	@PostMapping("/suppressionCompte")

	public String deleteAccount(@ModelAttribute Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", utilisateur);
		utilisateurRepo.delete(utilisateur);
		return "test";
	}

	@GetMapping("/utilisateur/{id}")

	public String getUser(@PathVariable("id") int id, Model model) {

		model.addAttribute("id", id);
		Utilisateur user = utilisateurRepo.getOne(id);
		System.out.println(user.toString());
		model.addAttribute("user", user);
		return "test";
	}

	@GetMapping("/utilisateurs")

	public String getUsers(Model model) {

		List<Utilisateur> liste = utilisateurRepo.findAll();
		Utilisateur user0 = liste.get(0);
		Utilisateur user1 = liste.get(1);
		Utilisateur user2 = liste.get(2);
		System.out.println(user0.toString());
		System.out.println(user1.toString());
		System.out.println(user2.toString());
		return "test1";
	}
	
	
	@GetMapping("/user/{name}")

	public String getUsersByName(@PathVariable("name") Sort name, Model model) {

		//Utilisateur user= utilisateurRepo.findByNom(name);
		List<Utilisateur> liste = utilisateurRepo.findAll(name);
		Utilisateur user0 = liste.get(0);
		System.out.println(user0.toString());
		return "test1";
	}

	
	
	

}
