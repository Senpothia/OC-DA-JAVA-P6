package com.formation.escalade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String selection(@PathVariable("id") Integer id) {
		siteService.chercherSite(id);
		return "test1";
	}
	
}
