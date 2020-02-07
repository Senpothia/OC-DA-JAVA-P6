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

		return "index";
	}

	@GetMapping("/addsecteur")
	public String addsecteur(Model model) {
		model.addAttribute("formSite", new FormSite());
		return "add_secteur";
	}

	@GetMapping("/addvoie")
	public String addvoie() {

		return "creation_voie";
	}

	@GetMapping("/addlongueur")
	public String addlongueur() {

		return "creation_longueur";
	}

	@GetMapping("/visualisersite")
	public String visualiserSite() {

		return "index";
	}

	@GetMapping("/modifiersite")
	public String modifierSite() {

		return "index";
	}

	@GetMapping("/selection/{id}")
	public String selection(@PathVariable("id") Integer id, Model model) {
		/**
		 * List<LigneSite> tableSite = siteService.chercherSite(id); String nomSite =
		 * new String(); nomSite = tableSite.get(0).getNomSite(); List<String>
		 * listeSecteurs = siteService.ordonnerSecteur(tableSite); List<String>
		 * listeVoies = siteService.ordonnerVoie(tableSite);
		 */
		
		/**
		Site site = siteRepo.getOne(id);
		// Traitement des secteurs
		List<Secteur> secteurs = secteurRepo.findBySite(site);  // Récupération de tous les secteurs du site
		
		for (Secteur s : secteurs) {  // Affichage de chaque secteur

			System.out.println(s.toString());
		}
		// Traitement des voies 
		List<Voie> voies = new ArrayList<Voie>();
		List<List<Voie>> listeVoies = new ArrayList<List<Voie>>();

		for (Secteur s : secteurs) {
			voies = voieRepo.findBySecteur(s);  // Récupération de toutes les voies d'un secteur
			listeVoies.add(voies);  // Une liste de voie par secteur dans une liste globale de voie (listeVoies)
		}

		for (List<Voie> v : listeVoies) {
				for (Voie w: v) {
					System.out.println(w.toString());  // Affichage de toutes les voies 
				}
			
		}

		List<Longueur> longueurs = new ArrayList<Longueur>();
		List<List<Longueur>> listeLongueurs = new ArrayList<List<Longueur>>();

		for (List<Voie> v: listeVoies) {
			
			for (Voie w: v) {
				longueurs = longueurRepo.findByVoie(w);
				listeLongueurs.add(longueurs); 
				
			}
		}
		
		for (List<Longueur> l : listeLongueurs) {
			for (Longueur j: l) {
				System.out.println(j.toString());  // Affichage de toutes les voies 
			}
		
	}
		*/
		siteService.decomposerSite(id);
		return "selection";

	} // fin

}