package com.formation.escalade.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IVoie;


@Service
public class GeneraService{

  @Autowired
	GeneralService generalService;
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

	/**
	 * private Site site; private List<Secteur> secteurs; private List<Voie> voies;
	 * private List<Longueur> longueurs;
	 * 
	 */

	public GeneralService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}
  
   public pagination(Model model){
   
   List<String> nomsSites = new ArrayList<>();
		List<Site> sites = siteRepo.findAll();
		for (Site site: sites) {
			nomsSites.add(site.getNom());
		}
		
		int taille = nomsSites.size();
		//taille = taille + 29;  //    test calcul nbre de pages 
		System.out.println("nbre de sites: " + taille);
		int nbrePages = taille / 10 ;
		int reste = taille % 10;
		int borneSup = 5;
		
		if (reste != 0) {
			
			nbrePages++;
		}
		
		System.out.println("nbre de pages: " + nbrePages);
		
		List<String> ligne1 = new ArrayList<>();
		
		if (taille<5){
		
			borneSup = taille;
		}
		
		for (int i=0; i<borneSup; i++) {
			
			ligne1.add(nomsSites.get(i));
		}
		
		model.addAttribute("ligne1", ligne1);
		
		List<String> ligne2 = new ArrayList<>();
		
		
		
		if (nbrePages < 2) {
			
			borneSup = taille - 5;
		}
		for (int i=0; i< borneSup; i++) {
			
			ligne2.add(nomsSites.get(i));
		}
		model.addAttribute("ligne2", ligne2);
		
		List<String> numPages = new ArrayList<>();
		for (int i=1; i<nbrePages+1; i++){
			String numPage = String.valueOf(i);
			System.out.println("num de page: " + numPage);
			numPages.add(numPage);
		
		}
		model.addAttribute("numPages", numPages);
   
   }
 

}
