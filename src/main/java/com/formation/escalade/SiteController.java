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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.GeneralService;
import com.formation.escalade.service.SiteService;

@Controller
public class SiteController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;

	final int LIGNE = 5; // nombre de site afficher par ligne de la galerie

	@Autowired
	SiteService siteService;

	@Autowired
	GeneralService generalService;

	public SiteController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
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

	@GetMapping("/structure/site/{id}")
	public String structureSite(@PathVariable("id") Integer id, Model model,HttpServletRequest request,
			Principal principal) {
		
		System.out.println("entrée structureSite()");

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
		System.out.println("Nom du site: " + site.getNom());
		model.addAttribute("site", site);
		return "structure";
	}

	@GetMapping("/modifier/site/{id}")
	public String modifierSite(@PathVariable("id") Integer id, Model model) {

		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		return "arbre";
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
	public String galeriePage(@PathVariable("page") int page, Model model, HttpServletRequest request,
			Principal principal) {
		System.out.println("entrée /galerie");

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		generalService.pagination(page, model);
		return "galerie";

	}

	@GetMapping("/viewsite/{nomSite}")
	public String vueSite(@PathVariable("nomSite") String nomSite, Model model, HttpServletRequest request,
			Principal principal) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		Site site = siteRepo.findByNom(nomSite);
		model.addAttribute("site", site);

		return "site";

	}

	@GetMapping("/commentaires/site/{id}")
	public String getComments(@PathVariable("id") Integer id, Model model, HttpServletRequest request, Principal principal) {
		System.out.println("entrée getComments()");
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
		List<Commentaire> commentaires = site.getCommentaires();
		model.addAttribute("commentaires", commentaires);
		model.addAttribute("site", site);
		
		return "commentaires";

	}

	@GetMapping("/commenter/site/{id}")
	public String commenter(@PathVariable("id") Integer id, Model model, 
			HttpServletRequest request, HttpSession session) {
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
		session.setAttribute("IDSITE", id);
		model.addAttribute("site", site);
		

		return "commenter";

	}

	@PostMapping("/commenter")
	public String saveComment(String comment, HttpServletRequest request, Model model) {
		
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		System.out.println("Commentaire reçu:" + comment);
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		System.out.println("site id: " + siteId);
		Commentaire commentaire = new Commentaire();
		Utilisateur auteur = new Utilisateur();
		auteur = utilisateurRepo.getOne(1);
		commentaire.setAuteur(auteur);
		Site site = siteRepo.getOne(siteId);
		commentaire.setSite(site);
		commentaire.setText(comment);
		commentaireRepo.save(commentaire);
		model.addAttribute("site", site);
		
		return "site";
	}

	@GetMapping("/commentaire/supprimer/comment")
	public String supprimerCommentaire(@RequestParam("siteId") Integer siteId, @RequestParam("num") int num) {

		Site site = siteRepo.getOne(siteId);
		List<Commentaire> commentaires = site.getCommentaires();
		Commentaire commentaire = commentaires.get(num);
		System.out.println(commentaire.getText());
		commentaireRepo.delete(commentaire);
		return "ok";
	}

	@GetMapping("/commentaire/modifier/comment")
	public String modifierCommentaire(@RequestParam("siteId") Integer siteId, @RequestParam("num") int num, Model model,
			HttpSession session) {
		session.setAttribute("IDSITE", siteId);
		Site site = siteRepo.getOne(siteId);
		List<Commentaire> commentaires = site.getCommentaires();
		Commentaire commentaire = commentaires.get(num);
		System.out.println(commentaire.getText());
		model.addAttribute("commentaire", commentaire);
		String comment = commentaire.getText();
		session.setAttribute("COMMENT", commentaire);
		model.addAttribute("site", site);
		model.addAttribute("comment", comment);
		return "modifiercommentaire";
	}

	@PostMapping("/commentaire/modification")

	public String modificationCommentaire(String comment, HttpServletRequest request) {
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		Commentaire commentaire = (Commentaire) request.getSession().getAttribute("COMMENT");
		System.out.println(comment);
		commentaire.setText(comment);
		commentaireRepo.save(commentaire);
		return "ok";
	}

	// ******** Methodes de test
	// *****************************************************

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

	@GetMapping("/ok")
	public String ok() { // Méthode de test

		return "ok";
	}
}
