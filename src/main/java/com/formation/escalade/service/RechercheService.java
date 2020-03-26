package com.formation.escalade.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSearch;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;

@Service
public class RechercheService {

	
	@Autowired
	private final ISite siteRepo;
	@Autowired
	private final ISecteur secteurRepo;
	@Autowired
	private final IVoie voieRepo;
	@Autowired
	private final ILongueur longueurRepo;
	@Autowired
	private final ICommentaire commentaireRepo;
	@Autowired
	private final IUtilisateur utilisateurRepo;
	
	
	public RechercheService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
	}
	
	public Set<Site> recherche(FormSearch formSearch) {
		
		Set<Site> sites = new LinkedHashSet<>(new ArrayList<Site>()); // liste à transmettre page html
		
		try {
		// recherche par nom de site
		Site site = siteRepo.findByNom(formSearch.getNom());
		if (site != null) {
			
			sites.add(site);
		}
		
		}catch(NullPointerException e) {
			
			System.out.println("aucune réponse");
		}
		
		try {
		// recherche par nom de secteur
		Secteur secteur = secteurRepo.findByNom(formSearch.getNom());
		sites.add(secteur.getSite());
		}catch(NullPointerException e) {}
		try {
		// recherche par nom de voie
		
		Voie voie = voieRepo.findByNom(formSearch.getNom());
		Secteur secteur1 = voie.getSecteur();
		sites.add(secteur1.getSite());
		} catch(NullPointerException e) {}
		
		try {
		// recherche par nom de longueur
		
		Longueur longueur = longueurRepo.findByNom(formSearch.getNom());
		Voie voie1 = longueur.getVoie();
		Secteur secteur2 = voie1.getSecteur();
		sites.add(secteur2.getSite());
		} catch(NullPointerException e) {}
		
		// Recherche par nom de createur
		
		try {
			
			List<Utilisateur> createurs = utilisateurRepo.findByNomOrPrenomIgnoreCase(formSearch.getNom(), formSearch.getNom());
			for (Utilisateur createur : createurs) {
				
				Integer id = createur.getId();
				List<Site> sites1 = siteRepo.findByCreateur(id);
				sites.addAll(sites1);
			}
			
		} catch(NullPointerException e) {}
		
		//  
		
		return sites; 
	}

	
	
	public List<Utilisateur> rechercheCreateur(Set<Site> sites){
		
		List<Utilisateur> createurs = new ArrayList<Utilisateur>();
		
		for (Site site : sites) {

			Integer idCreateur = site.getCreateur();
			Utilisateur createur = utilisateurRepo.getOne(idCreateur);
			createurs.add(createur);
		}
		
		System.out.println("taille de la liste createur, rechercheCreateur dans service: " + sites.size());
		return createurs; 
	}
	
	
}
 
