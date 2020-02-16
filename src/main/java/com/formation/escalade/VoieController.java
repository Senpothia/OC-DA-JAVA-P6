package com.formation.escalade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.Site;
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.SiteService;

@Controller
public class VoieController {
	
	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final CommentaireRepo commentaireRepo;

	

	public VoieController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {

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
	
	@PostMapping("/choisirsecteur")
	public String choixSite(String nomSecteur, HttpServletRequest request, Model model) {
		
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		System.out.println("nomSecteur: " + nomSecteur);
		System.out.println("Id site: " + siteId);
		//Site site = siteRepo.findByNom(nomSite);
		//model.addAttribute("site", site);

		return "ok";
	}

}
