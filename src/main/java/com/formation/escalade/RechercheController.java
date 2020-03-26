package com.formation.escalade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.Element;
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
import com.formation.escalade.service.RechercheService;

@Controller
public class RechercheController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
	private final ITopo topoRepo;

	@Autowired
	RechercheService rechercheService;

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

		Boolean vide = true;
		Set<Site> sites = new LinkedHashSet<>(new ArrayList<Site>()); // liste à transmettre page html
		// Recherche sur éléments de site
		Site siteNom = siteRepo.findByNom(phrase);
		Element e1 = new Element(siteNom);
		Boolean siteNomPresent = e1.isPresent();
		System.out.println(" boolean sitePresent :" + siteNomPresent);
		List<Site> sitesLocalisation = siteRepo.findByLocalisation(phrase);
		Element e1a = new Element(sitesLocalisation);
		Boolean siteLocalisationPresent = e1a.isPresent();

		if (siteLocalisationPresent) {

			sites.addAll(sitesLocalisation);
		}

		if (siteNomPresent) {

			sites.add(siteNom);
		}

		// Recherche sur éléments de secteur
		Secteur secteurNom = secteurRepo.findByNom(phrase);
		Element e2 = new Element(secteurNom);
		Boolean secteurNomPresent = e2.isPresent();

		if (secteurNomPresent) {

			sites.add(secteurNom.getSite());
		}

		// Recherche sur éléments de voie
		Voie voieNom = voieRepo.findByNom(phrase);
		Element e3 = new Element(voieNom);
		Boolean voiesNomPresent = e3.isPresent();
		List<Voie> voiesCotation = voieRepo.findByCotation(phrase);
		Element e4 = new Element(voiesCotation);
		Boolean voiesCotationPresent = e4.isPresent();

		if (voiesCotationPresent) {

			List<Secteur> secteursVoie = new ArrayList<Secteur>();
			for (Voie voie : voiesCotation) {

				secteursVoie.add(voie.getSecteur());
			}
			List<Site> sitesVoie = new ArrayList<Site>();
			for (Secteur secteur : secteursVoie) {

				sitesVoie.add(secteur.getSite());
			}

			sites.addAll(sitesVoie);
		}

		if (voiesNomPresent) {

			Secteur secteur = voieNom.getSecteur();
			Site site = secteur.getSite();
			sites.add(site);
		}

		// Recherche sur éléments de longueur
		Longueur longueurNom = longueurRepo.findByNom(phrase);
		Element e5 = new Element(longueurNom);
		Boolean longueurNomPresent = e5.isPresent();
		List<Longueur> longueursCotation = longueurRepo.findByCotation(phrase);
		Element e6 = new Element(longueursCotation);
		Boolean longueursCotationPresent = e6.isPresent();
		Set<Longueur> setLongueurs = new LinkedHashSet<>(new ArrayList<Longueur>());
		if (longueurNomPresent) {

			setLongueurs.add(longueurNom);

		}

		if (longueursCotationPresent) {

			setLongueurs.addAll(longueursCotation);

			Set<Voie> setVoiesLongueurs = new LinkedHashSet<>(new ArrayList<Voie>());
			for (Longueur longueur : setLongueurs) {

				setVoiesLongueurs.add(longueur.getVoie());

			}

			Set<Secteur> setSecteursLongueurs = new LinkedHashSet<>(new ArrayList<Secteur>());
			for (Voie voie : setVoiesLongueurs) {

				setSecteursLongueurs.add(voie.getSecteur());
			}

			// Set<Site> setSitesLongueurs = new LinkedHashSet<>(new ArrayList<Site>());
			for (Secteur secteur : setSecteursLongueurs) {

				sites.add(secteur.getSite());

			}
		}

		// Recherche sur éléments d'utilisateur
		// Utilisateur utilisateurNomPrenom = utilisateurRepo.findByNomOrPrenom(phrase,
		// phrase);
		Utilisateur utilisateurNomPrenom = utilisateurRepo.findByNomOrPrenomIgnoreCase(phrase, phrase);
		Element e7 = new Element(utilisateurNomPrenom);
		Boolean UtiliseurNomPrenomPresent = e7.isPresent();
		List<Site> sitesCreateur = new ArrayList<Site>();
		if (UtiliseurNomPrenomPresent) {

			Integer id = utilisateurNomPrenom.getId();
			sitesCreateur = siteRepo.findByCreateur(id);
			sites.addAll(sitesCreateur);
		}

		// Recherche sur éléments de topo
		Topo topoNom = topoRepo.findByNom(phrase);
		Element e8 = new Element(topoNom);
		Boolean topoNomPresent = e8.isPresent();

		List<Topo> toposDescription = topoRepo.findByDescription(phrase);
		Element e9 = new Element(toposDescription);
		Boolean toposDescriptionPresent = e9.isPresent();

		List<Topo> toposLieu = topoRepo.findByLieu(phrase);
		Element e10 = new Element(toposLieu);
		Boolean toposLieuPresent = e10.isPresent();
		Set<Topo> setTopos = new LinkedHashSet<>(new ArrayList<Topo>());
		if (toposDescriptionPresent) {

			setTopos.addAll(toposDescription);
		}

		if (toposLieuPresent) {

			setTopos.addAll(toposLieu);
		}

		if (topoNomPresent) {

			setTopos.add(topoNom);
		}

		List<Site> sitesTopo = new ArrayList<Site>();
		for (Topo topo : setTopos) {

			sites.add(topo.getSite());

		}

		System.out.println("Taille liste des sites: " + sites.size());

		if (sites.size() != 0) {

			vide = false;
		}

		model.addAttribute("sites", sites);
		List<Utilisateur> createurs = new ArrayList<Utilisateur>();
		for (Site site : sites) {

			Integer idCreateur = site.getCreateur();
			Utilisateur createur = utilisateurRepo.getOne(idCreateur);
			createurs.add(createur);
		}

		model.addAttribute("createurs", createurs);
		model.addAttribute("phrase", phrase);
		model.addAttribute("vide", vide);

		return "resultats";

	}

	@GetMapping("/recherche/avancee")
	public String rechercheAvancee(Model model, HttpServletRequest request, Principal principal) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		

		return "recherche";
	}

}
