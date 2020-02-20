package com.formation.escalade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.SiteService;

@Controller
public class SiteController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final CommentaireRepo commentaireRepo;

	@Autowired
	SiteService siteService;

	public SiteController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	@GetMapping("/creationsite")

	public String creationSite(Model model) {

		model.addAttribute("formSite", new FormSite());

		return "creation_site";
	}

	@PostMapping("/creationsite")

	public String siteSubmit(FormSite formSite) {

		siteService.createSite(formSite);

		return "creation_site";
	}

	@GetMapping("/annuler")
	public String annulation() {

		return "espace";
	}

	@GetMapping("/visualisersite")
	public String visualiserSite() {

		return "site";
	}

	@GetMapping("/modifiersite")
	public String modifierSite() {

		return "index";
	}


	@GetMapping("/choisirsite")
	public String choisirSite(Model model) {

		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);
		return "choisirsite";
	}

	@PostMapping("/choisirsite")
	public String choixSite(String nomSite, Model model) {

		Site site = siteRepo.findByNom(nomSite);
		model.addAttribute("site", site);

		return "arbre";
	}
	
	@GetMapping("/galerie")
	public String galerie(Model model) {
		
		List<String> nomsSites = new ArrayList<>();
		List<Site> sites = siteRepo.findAll();
		for (Site site: sites) {
			nomsSites.add(site.getNom());
		}
		
		int taille = nomsSites.size();
		//taille = taille + 29;  //    test calcul nbre de pages 
		System.out.println("nbre de sites: " + taille);
		int nbrePages = taille / 10 ;
		int reste = taille % 10;
		int borneSup = 5;
		
		if (reste != 0) {
			
			nbrePages++;
		}
		
		System.out.println("nbre de pages: " + nbrePages);
		
		List<String> ligne1 = new ArrayList<>();
		
		if (taille<5){
		
			borneSup = taille;
		}
		
		for (int i=0; i<borneSup; i++) {
			
			ligne1.add(nomsSites.get(i));
		}
		
		model.addAttribute("ligne1", ligne1);
		
		List<String> ligne2 = new ArrayList<>();
		
		
		
		if (nbrePages < 2) {
			
			borneSup = taille - 5;
		}
		for (int i=0; i< borneSup; i++) {
			
			ligne2.add(nomsSites.get(i));
		}
		model.addAttribute("ligne2", ligne2);
		
		List<String> numPages = new ArrayList<>();
		for (int i=1; i<nbrePages+1; i++){
			String numPage = String.valueOf(i);
			numPages.add(numPage);
		
		}
		model.addAttribute("NumPages", numPages);
		return "galerie";
	}
	
	
	// ********   Methodes de test  *****************
	
	@PostMapping("/ok")
	public String choixsite(String nomSite) { // Méthode pour test

		System.out.println("Site choisi: " + nomSite);

		return "ok";
	}
	
	@GetMapping("/selection4")
	public String resume(Model model) { // Méthode de test

		Site site = siteRepo.getOne(1);
		model.addAttribute("site", site);

		return "selection4";
	}
	
	@GetMapping("/nombre")
	public String nombre(Model model) { // Méthode de test

		List<String> nomsSites = new ArrayList<>();
		List<Site> sites = siteRepo.findAll();
		for (Site site: sites) {
			nomsSites.add(site.getNom());
		}
		model.addAttribute("nomsSites", nomsSites);
		return "nombre";
	}
	
	@GetMapping("/gal")
	public String nombre2(Model model) { // Méthode de test

		
		return "galerie2";
	}
}
