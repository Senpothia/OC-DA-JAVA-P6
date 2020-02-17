package com.formation.escalade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class VoieService {

	@Autowired
	SecteurService secteurService;
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
	
	public VoieService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	public void creervoie(FormSite formSite, Integer siteId, String nomSecteur) {
		
		Site site = siteRepo.getOne(siteId);
		String nomVoie = formSite.getNomVoie();

		String cotationVoie = formSite.getCotationVoie();

		String nomLongueur = formSite.getNomLongueur();
		int nbreSpit = formSite.getNbreSpit();
		String cotationLongueur = formSite.getCotationLongueur();
		Voie voie = new Voie();
		voie.setNom(nomVoie);
		voie.setCotation(cotationVoie);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		voie.setSecteur(secteur);
		
		List<Voie> voies = secteur.getVoies();
		voies.add(voie);
		
		
		//voieRepo.save(voie);

		Longueur longueur = new Longueur();
		longueur.setNom(nomLongueur);
		longueur.setSpit(nbreSpit);
		longueur.setCotation(cotationLongueur);
		longueur.setVoie(voie);
		
		List<Longueur> longueurs = new ArrayList<>();
		longueurs.add(longueur);
		voie.setLongueurs(longueurs);
		secteurRepo.save(secteur);
		voieRepo.save(voie);
		longueurRepo.save(longueur);
		
	}

}
