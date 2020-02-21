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

	final int LIGNE = 5; // nombre de site afficher par ligne de la galerie

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

	@GetMapping("/galerie/{page}")
	public String galeriePage(@PathVariable("page") int page, Model model) {

		List<String> nomsSites = new ArrayList<>();
		List<Site> sites = siteRepo.findAll();
		for (Site site : sites) {
			nomsSites.add(site.getNom());
		}

		int taille = nomsSites.size(); // nombre de sites enregistrés
		// taille = taille + 29; // test calcul nbre de pages
		System.out.println("nbre de sites: " + taille);
		int nbrePages = taille / (2 * LIGNE); // nbre de pages pleines
		int reste = taille % (2 * LIGNE); // nbre de sites contenus dans page incomplète

		if (reste != 0) { // il reste des sites en plus deceux sur les pages pleines

			nbrePages++; // page suplémentaire pour les sites restants
		}

		System.out.println("nbre de pages: " + nbrePages);
		System.out.println("reste: " + reste);

		List<String> ligne1 = new ArrayList<>();
		List<String> ligne2 = new ArrayList<>();

		int borneInf = (page - 1) * 2 * LIGNE;
		int borneSup = borneInf + LIGNE - 1;

		if (page == nbrePages) { // traitement de la dernière page

			System.out.println("Traitement derniere page ");

			if (reste < LIGNE) { // Une seule ligne à remplir

				for (int i = borneInf; i < borneInf + reste; i++) {
					System.out.println("indice ligne partielle unique derniere page: " + i);
					ligne1.add(nomsSites.get(i)); // determiner l'indice i
				}

			} else {		// Deux lignes à remplir
				for (int i = borneInf; i < borneSup + 1; i++) { // Remplissage ligne 1 normalement
					System.out.println("indice ligne pleine deniere page: " + i);
					ligne1.add(nomsSites.get(i)); // determiner l'indice i
				}

				for (int i = borneInf + LIGNE; i < borneInf + LIGNE +(reste - LIGNE); i++) { // Remplissage ligne 2 partiellement
					System.out.println("indice ligne partielle derniere page: " + i);
					ligne2.add(nomsSites.get(i)); // determiner l'indice i
				}
			}
			
			model.addAttribute("ligne1", ligne1);
			model.addAttribute("ligne2", ligne2);
			
		} // fin traitement de la dernière page

		if (page < nbrePages) { // traitement page pleine

			for (int i = borneInf; i < borneSup + 1; i++) { // Remplissage lignes pleines - ligne 1
				System.out.println("indice ligne pleine: " + i);
				ligne1.add(nomsSites.get(i));
			}

			model.addAttribute("ligne1", ligne1);

			for (int i = borneInf + LIGNE; i < borneSup + LIGNE + 1; i++) { // Remplissage lignes pleines - ligne 2
				System.out.println("indice ligne pleine: " + i);
				ligne2.add(nomsSites.get(i));
			}

			model.addAttribute("ligne2", ligne2);
		} // fin traitement ligne pleine

		List<String> numPages = new ArrayList<>();
		for (int i = 1; i < nbrePages + 1; i++) { // Définition des numéro de page pour thymeleaf
			String numPage = String.valueOf(i);
			System.out.println("num de page thymeleaf pour pagination : " + numPage);
			numPages.add(numPage);

		}
		String previous = String.valueOf(page == 1 ? 1 : page - 1); // Détermination des numéro de pages
		String next = String.valueOf(page == nbrePages ? nbrePages : page + 1);// pour les boutons previous et next
		model.addAttribute("numPages", numPages);
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);

		for (int i = 0; i < ligne1.size(); i++) {

			System.out.println("ligne 1: " + i +" " + ligne1.get(i));
		}

		for (int i = 0; i < ligne2.size(); i++) {

			System.out.println("ligne 2: " + i +" "+ ligne2.get(i));
		}

		return "galerie";

	}

	// ******** Methodes de test *****************

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
		for (Site site : sites) {
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
