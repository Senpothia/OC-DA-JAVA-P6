package com.formation.escalade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
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
	
	public SiteController(ISite siteRepo, ISecteur secteurRepo,IVoie voieRepo, ILongueur longueurRepo,CommentaireRepo commentaireRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	@GetMapping("/creationSite")

	public String creationSite(Model model) {

		model.addAttribute("formSite", new FormSite());

		return "creation_site";
	}

	@PostMapping("/creationSite")

	public String siteSubmit(FormSite formSite) {
		
		siteService.createSite(formSite);;
		/**
		System.out.println(formSite.toString());

		String nomSite = formSite.getNomSite();
		String localisationSite = formSite.getLocalisationSite();
		int departementSite = formSite.getDepartementSite();
		boolean officielSite = false;
		
		String remSite = formSite.getRemSite();
		
		
		String nomSecteur = formSite.getNomSecteur();
		
		
		String nomVoie = formSite.getNomVoie();
		
		String cotationVoie = formSite.getCotationVoie();
		

		String nomLongueur = formSite.getNomLongueur();
		int nbreSpit = formSite.getNbreSpit();
		String cotationLongueur = formSite.getCotationLongueur();
		
		

		Site site = new Site();
		site.setNom(nomSite);
		site.setLocalisation(localisationSite);
		site.setDepartement(departementSite);
		site.setOfficiel(officielSite);
		
		siteRepo.save(site);
		
		Commentaire commentaire = new Commentaire();
		Utilisateur auteur = new Utilisateur();
		auteur.setId(1);
		commentaire.setAuteur(auteur);
		commentaire.setSite(site);
		commentaire.setText(remSite);
		commentaireRepo.save(commentaire);
		
		
		Secteur secteur = new Secteur();
		secteur.setNom(nomSecteur);
		secteur.setSite(site);
		
		secteurRepo.save(secteur);
		
		Voie voie = new Voie();
		voie.setNom(nomVoie);
		voie.setCotation(cotationVoie);
		voie.setSecteur(secteur);
		voieRepo.save(voie);
		
		Longueur longueur = new Longueur();
		longueur.setNom(nomLongueur);
		longueur.setSpit(nbreSpit);
		longueur.setCotation(cotationLongueur);
		longueur.setVoie(voie);

		longueurRepo.save(longueur);   
		*/
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
		
		return "index";
	}
	
	@GetMapping("/addlongueur")
	public String addlongueur() {
		
		return "index";
	}
	
	@GetMapping("/visualisersite")
	public String visualiserSite() {
		
		return "index";
	}
	
	@GetMapping("/modifiersite")
	public String modifierSite() {
		
		return "index";
	}
	
}
