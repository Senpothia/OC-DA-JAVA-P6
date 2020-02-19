package com.formation.escalade;

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
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.LongueurService;
import com.formation.escalade.service.SiteService;
import com.formation.escalade.service.VoieService;

@Controller
public class LongueurController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final CommentaireRepo commentaireRepo;

	@Autowired
	LongueurService longueurService;

	public LongueurController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	@GetMapping("/site/{id}/longueurs")
	public String ajouterLongueur(@PathVariable("id") Integer siteId, Model model, HttpSession session) {

		session.setAttribute("IDSITE", siteId);
		Site site = siteRepo.getOne(siteId);
		model.addAttribute("site", site);
		String nomSecteur = new String();
		model.addAttribute("nomSecteur", nomSecteur);
		return "choisirsecteur_long";

	}

	@PostMapping("/choisirvoie")
	public String choisirVoie(String nomSecteur, Model model, HttpSession session) {

		System.out.println("Choix secteur:" + nomSecteur);
		session.setAttribute("NOMSECTEUR", nomSecteur);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		String nomVoie = new String();
		model.addAttribute("nomVoie", nomVoie);

		return "choisirvoie";

	}

	@PostMapping("/creerlongueur")
	public String creerVoie(String nomVoie, Model model, HttpServletRequest request, HttpSession session ) {
		
		System.out.println("Nom de voie: " + nomVoie);
		Voie voie = voieRepo.findByNom(nomVoie);
		model.addAttribute("voie", voie );
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		Site site = siteRepo.getOne(siteId);
		String nomSecteur = (String) request.getSession().getAttribute("NOMSECTEUR");
		session.setAttribute("NOMVOIE", nomVoie);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		model.addAttribute("site", site);
		//FormSite formSite = new FormSite();
		model.addAttribute("formSite",  new FormSite() );
		
		return "creation_longueur";
	}
	@PostMapping("/creationlongueur")
	
	public String creationLongueur(FormSite formSite, HttpServletRequest request) {
		

		longueurService.createLongueur(formSite, request);
		return "ok";
	}
	
	
}
