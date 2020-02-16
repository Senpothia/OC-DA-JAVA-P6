package com.formation.escalade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Site;
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
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
	private final CommentaireRepo commentaireRepo;

	

	public SecteurController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}
	
	@GetMapping("/creation_secteur")
	public String addsecteur(Model model) {
		model.addAttribute("formSite", new FormSite());
		return "creation_secteur";
	}
	
	@GetMapping("/site/{id}/secteurs")
	public String modifierSecteur(@PathVariable("id") Integer idSite, Model model) {

	
		Site site = siteRepo.getOne(idSite);
		FormSite formSite = new FormSite();
		formSite.setIdSite(idSite);
		model.addAttribute("formSite", formSite );
		model.addAttribute("site", site);
		System.out.println("GET: " + formSite.toString());
		return "creation_secteur";
	}
	
	@PostMapping("/creationsecteur")

	public String siteSubmit(FormSite formSite, Site site) {

		System.out.println("POST: " +formSite.toString());
		System.out.println("Site id: " + site.getId());
		//secteurService.createSecteur(formSite, site);

		return "ok";
	}

}
