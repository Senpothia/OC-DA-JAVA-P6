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
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
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

	@Autowired
	VoieService voieService;

	public VoieController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
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
	public String ajoutervoie(FormSite formSite, Site site,  HttpServletRequest request, Model model) {
		
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		String nomSecteur = (String) request.getSession().getAttribute("NOMSECTEUR");
		System.out.println("POST: " + formSite.toString());
		System.out.println("Site id session: " + siteId);
		voieService.creervoie(formSite, siteId, nomSecteur);
		Site siteActuel = siteRepo.getOne(siteId);
		model.addAttribute("site", siteActuel);
		return "arbre";
		
	}
	
	


}
