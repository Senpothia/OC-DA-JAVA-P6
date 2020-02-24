package com.formation.escalade;

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
import com.formation.escalade.model.FormTopo;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
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
import com.formation.escalade.service.GeneralService;
import com.formation.escalade.service.SiteService;


@Controller
public class TopoController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
  private final ITopo topoRepo;

	/**
  @Autowired
	SiteService siteService;

	@Autowired
	GeneralService generalService;
  */

	public TopoController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo, ITopo topoRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
    		this.topoRepo = topoRepo;
	}
	
	@GetMapping("/creationtopo")
	public String creationTopo(Model model){
		
		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		model.addAttribute("formTopo", new FormTopo());
	
		return "creation_topo";
	}
	
	@PostMapping("/creationtopo")
	public String retourFormTopo(FormTopo formTopo){
	
		System.out.println(formTopo.toString());
		Topo topo = new Topo();
		topo.setNom(formTopo.getNom());
		topo.setDescription(formTopo.getDescription());
		topo.setLieu(formTopo.getLieu());
		topo.setDate(formTopo.getDate());
		topo.setDisponible(formTopo.isDisponibilite());
		Site site = siteRepo.findByNom(formTopo.getNomSite());
		Integer id = site.getId();
		topo.setId_site(id);
		topo.setId_utilisateur(1);
		topoRepo.save(topo);
		return "ok"; 
	}
	
	@GetMapping("/choisirtopo")
	public String selectionSiteTopo (Model model) {
		
		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);
	
		return"choisirtopo";
		
	}
	
	@PostMapping("/choisirtopo")
	public String choixSite(String nomSite, Model model) {
		
		System.out.println(nomSite);
		Site site = siteRepo.findByNom(nomSite);
		Integer id_site = site.getId();
		List<Topo> topos = topoRepo.findByIdSite(id_site);
		//List<Topo> topos = topoRepo.findByLieu("Mantes");
		for (int i=0; i<topos.size();i++) {
			
			System.out.println("Visu topo: " + topos.get(i).toString());
		}
		
		model.addAttribute("topos", topos);

		return "ok";
	}
  
  }
