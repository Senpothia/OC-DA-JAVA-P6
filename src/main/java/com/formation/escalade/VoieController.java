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
import com.formation.escalade.service.SiteService;
import com.formation.escalade.service.VoieService;

@Controller
public class VoieController {
	
	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
	
	@Autowired
	VoieService voieService;

	public VoieController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
	}
	
	@GetMapping("/site/{id}/voies")
	public String addvoie(@PathVariable("id") Integer idSite, Model model, HttpSession session) {
		
		Integer siteId = idSite;
		session.setAttribute("IDSITE", siteId);
		Site site = siteRepo.getOne(idSite);
		model.addAttribute("site", site);
		String nomSecteur = new String();
		model.addAttribute("nomSite", nomSecteur);
		return "choisirsecteur";
	}
	
	@PostMapping("/creervoie")
	public String choixSite(String nomSecteur, HttpServletRequest request, Model model, HttpSession session) {
		
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		System.out.println("nomSecteur: " + nomSecteur);
		System.out.println("Id site: " + siteId);
		session.setAttribute("NOMSECTEUR", nomSecteur);
		//Site site = siteRepo.findByNom(nomSite);
		//model.addAttribute("site", site);
		Site site = siteRepo.getOne(siteId);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("site", site);
		model.addAttribute("secteur", secteur);
		model.addAttribute("formSite", new FormSite());

		return "creation_voie";
	}
	
	@PostMapping("/ajoutervoie")
	public String ajoutervoie(FormSite formSite, Site site,  HttpServletRequest request
			, Model model, Principal principal) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		String nomSecteur = (String) request.getSession().getAttribute("NOMSECTEUR");
		System.out.println("POST: " + formSite.toString());
		System.out.println("Site id session: " + siteId);
		Boolean creation = voieService.creervoie(formSite, siteId, nomSecteur);
		
		if (creation) {
			
			Site siteActuel = siteRepo.getOne(siteId);
			siteRepo.save(siteActuel);
			model.addAttribute("site", siteActuel);
			
			return "espace";
		}else {
			
			model.addAttribute("erreur", true);
			
			Site siteActuel = siteRepo.getOne(siteId);
			model.addAttribute("site", siteActuel);
			Secteur secteur = secteurRepo.findByNom(nomSecteur);			
			model.addAttribute("secteur", secteur);
			model.addAttribute("formSite", new FormSite());
			return "creation_voie";
			
		}
		
	}
	
	@PostMapping("/supprimer/site/voie/{id}")
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
		
		System.out.println("nom secteur récupéré à supprimer***: " + nomSecteur);
		
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		model.addAttribute("del_voie", true);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		
		return "choisirvoie_delete";
	}

	@PostMapping("/supprimer/site/secteur/voie/{id}")
	public String supprimerVoie(@PathVariable("id") Integer id, Model model
			, HttpSession session, Principal principal
			, HttpServletRequest request, String nomVoie) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("nom voie récupéré à supprimer***: " + nomVoie);
		
		Voie voie = voieRepo.findByNom(nomVoie);
		List<Longueur> longueurs = longueurRepo.findByVoie(voie);
		longueurRepo.deleteAll(longueurs);
		voieRepo.delete(voie);
		return "espace";
	}
}
