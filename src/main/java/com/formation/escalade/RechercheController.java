package com.formation.escalade;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Topo;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.ITopo;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;

@Controller
public class RechercheController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
	private final ITopo topoRepo;

	public RechercheController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo, ITopo topoRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
		this.topoRepo = topoRepo;
	}

	@PostMapping("/rechercher")
	public String rechercher(Model model, HttpServletRequest request, Principal principal, String phrase) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("Phrase: " + phrase);
		
		
		// Recherche sur éléments de site 
		Site site = siteRepo.findByNom(phrase);
		List<Site> sites = siteRepo.findByLocalisation(phrase);
		
		
		// Recherche sur éléments de secteur
		Secteur secteur = secteurRepo.findByNom(phrase);
		
		
		// Recherche sur éléments de voie
		Voie voie = voieRepo.findByNom(phrase);
		List<Voie> voies = voieRepo.findByCotation(phrase);
 		
		
		// Recherche sur éléments de longueur
		Longueur longueur = longueurRepo.findByNom(phrase);
		List<Longueur> longueurs = longueurRepo.findByCotation(phrase);
		
		
		// Recherche sur éléments d'utilisateur
		Utilisateur utilisateur = utilisateurRepo.findByNomOrPrenom(phrase, phrase);
		
		
		
		// Recherche sur éléments de topo
		Topo topo = topoRepo.findByNom(phrase);
		List<Topo> toposDescription = topoRepo.findByDescription(phrase);
		List<Topo> toposLieu = topoRepo.findByLieu(phrase);
		
		
		
		
		
		
		
		
		
		
		
		
		return "ok";
	}

}
