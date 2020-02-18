package com.formation.escalade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.SecteurService;

@Service
public class LongueurService {
	
	
	
	@Autowired
	private final ISite siteRepo;
	@Autowired
	private final ISecteur secteurRepo;
	@Autowired
	private final IVoie voieRepo;
	@Autowired
	private final ILongueur longueurRepo;
	@Autowired
	private final CommentaireRepo commentaireRepo;
	
	public LongueurService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	public void createLongueur(FormSite formSite, HttpServletRequest request) {
		
		Longueur longueur = new Longueur();
		
		String nomLongueur = formSite.getNomLongueur();
		String cotationLongueur = formSite.getCotationLongueur();
		int nbreSpits = formSite.getNbreSpit();
		System.out.println("nom: " + nomLongueur);
		System.out.println("cotation: " + cotationLongueur);
		System.out.println("nbre spits: " + nbreSpits);
		
		longueur.setNom(nomLongueur);
		longueur .setCotation(cotationLongueur);
		longueur.setSpit(nbreSpits);
		
		String nomVoie = (String) request.getSession().getAttribute("NOMVOIE");
		System.out.println("Nom voie: " + nomVoie);
		Voie voie = voieRepo.findByNom(nomVoie);
		longueur.setVoie(voie);
		
		longueurRepo.save(longueur);
		
	}

	
}
