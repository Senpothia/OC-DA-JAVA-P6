package com.formation.escalade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.SecteurService;
import com.formation.escalade.service.SiteService;

@Controller
public class SecteurController {

	@Autowired
	SecteurService secteurService;

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;

	public SecteurController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
	}

	@GetMapping("/creation_secteur")
	public String addsecteur(Model model) {
		model.addAttribute("formSite", new FormSite());
		return "creation_secteur";
	}

	@GetMapping("/site/{id}/secteurs")
	public String modifierSecteur(@PathVariable("id") Integer idSite, Model model, HttpSession session) {

		Integer siteId = idSite;
		session.setAttribute("IDSITE", siteId);
		// Integer siteId = (Integer) session.getAttribute("IDSITE");
		Site site = siteRepo.getOne(idSite);
		FormSite formSite = new FormSite();
		formSite.setIdSite(idSite);
		model.addAttribute("formSite", formSite);
		model.addAttribute("site", site);
		model.addAttribute("siteId", siteId);
		System.out.println("GET: " + formSite.toString());
		return "creation_secteur";
	}

	@PostMapping("/creationsecteur")

	public String secteurSubmit(FormSite formSite, Site site, HttpServletRequest request, Model model) {

		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		System.out.println("POST: " + formSite.toString());
		System.out.println("Site id session: " + siteId);
		Boolean creation = secteurService.createSecteur(formSite, siteId);


		if (creation) {

			Site siteActuel = siteRepo.getOne(siteId);
			model.addAttribute("site", siteActuel);
			return "arbre";

		} else {

			model.addAttribute("erreur", true);
			return "creation_secteur";
		}
	}
	
	@GetMapping("/supprimer/site/details/{id}")
	public String choixSecteur_del(@PathVariable("id") Integer id
			,@RequestParam(name = "secteur", required=false) Boolean del_secteur
			,@RequestParam(name="voie", required=false) Boolean del_voie
			,@RequestParam(name="longueur", required=false) Boolean del_longueur
			, Model model
			, HttpSession session, Principal principal
			, HttpServletRequest request) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Site site = siteRepo.getOne(id);
		List<Secteur> secteurs = site.getSecteurs();
		model.addAttribute("site", site);
		model.addAttribute("del_secteur", del_secteur);
		model.addAttribute("del_voie", del_voie);
		model.addAttribute("del_longueur", del_longueur);
		return "choisirsecteur_delete";
	}
	
	@PostMapping("/supprimer/site/secteur/{id}")
	public String supprimerSecteur(@PathVariable("id") Integer id, Model model
			, HttpSession session, Principal principal
			, HttpServletRequest request, String nomSecteur) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		
		System.out.println("nom secteur récupéré à supprimer: " + nomSecteur);
		
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		List<Voie> voies = secteur.getVoies();
		for (Voie voie : voies) {
			
			longueurRepo.deleteAll(voie.getLongueurs());
		}
		
		voieRepo.deleteAll(voies);
		secteurRepo.delete(secteur);
		
		return "espace";
	}

}
