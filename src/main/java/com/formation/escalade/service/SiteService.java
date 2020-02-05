package com.formation.escalade.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.formation.escalade.repository.IVoie;

@Service
public class SiteService implements GestionSiteService {
	
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
	
	private Site site;
	private List<Secteur> secteurs;
	private List<Voie> voies;
	private List<Longueur> longueurs;
	
	
	

	public SiteService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}


	@Override
	public void createSite(FormSite formSite) {
		
		
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
		
	}

	@Override
	public void updateSite(Integer id, Site site) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSite(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Site> getSite() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createSite(Site site) {
		// TODO Auto-generated method stub
		
	}
	
	public void arbre(Integer id) {
		
		this.site = siteRepo.getOne(id);
		this.secteurs = secteurRepo.findAllById_site();
		this.voies = voieRepo.findAllBySecteurs( this.secteurs);
		
		
		
	}

	
	

}
