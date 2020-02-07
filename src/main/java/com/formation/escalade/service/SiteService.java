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
	
	
	/**
	private Site site;
	private List<Secteur> secteurs;
	private List<Voie> voies;
	private List<Longueur> longueurs;
	
	*/
	

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


	@Override
	public void chercherSite(Integer id) {
		/**
		//  	Récuperation du site 
		String nomSite = new String();
		Site site = siteRepo.getOne(id);
		System.out.println("***************************");
		System.out.println(site.toString());
		nomSite = site.getNom();
		System.out.println(nomSite);
		System.out.println("***************************");
	
		//     Traitements des secteurs
		List<Secteur> secteurs = secteurRepo.findBySite(site);
		for (Secteur s: secteurs){
			System.out.println("--------------------------");
			System.out.println(s.toString());
			System.out.println("--------------------------");
		}
		
		ArrayList<String> nomsSecteurs = new ArrayList<String>();
		String nomSecteur = new String();
		for (Secteur s: secteurs){
			nomSecteur = s.getNom();
			System.out.println("++++++++++++++++++++++++++");
			System.out.println(nomSecteur);
			System.out.println("++++++++++++++++++++++++++");
			nomsSecteurs.add(nomSecteur);
		}
		
		//        Traitement des voies
		ArrayList<String> nomsVoies = new ArrayList<String>();
		List<Voie> voies = new ArrayList<Voie>();
		for (Secteur s: secteurs) {
		 voies = voieRepo.findBySecteur(s);
		for (Voie v: voies){
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvv");
			System.out.println(v.toString());
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvv");
		}
		
		String nomVoie = new String();
		for (Voie v: voies){
			nomVoie = v.getNom();
			System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuu");
			System.out.println(nomVoie);
			System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuu");
			nomsVoies.add(nomVoie);
			
		}
		
		}  
		//      Traitement des longueurs
		List<Longueur> longueurs = new ArrayList<Longueur>();
		ArrayList<String> nomsLongueurs = new ArrayList<String>();
		
		for (Voie v: voies) {
			longueurs = longueurRepo.findByVoie(v);
			for (Longueur l: longueurs){
				System.out.println("llllllllllllllllllllllllll");
				System.out.println(l.toString());
				System.out.println("llllllllllllllllllllllllll");
			}
			//secteurs = secteurRepo.findBySite(site);
			
			String nomLongueur = new String();
			for (Longueur l: longueurs){
				nomLongueur = l.getNom();
				System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjj");
				System.out.println(nomLongueur);
				System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjj");
				nomsLongueurs.add(nomLongueur);
				
			}
			}  
		
		
	*/
		
		
		List<LigneSite> tableSite = new ArrayList<LigneSite>();
		Site site = siteRepo.getOne(id);
		String nomSite = new String();
		nomSite = site.getNom();
		List<Secteur> secteurs = secteurRepo.findBySite(site);
		ArrayList<String> nomsSecteurs = new ArrayList<String>();
		String nomSecteur = new String();
		for (Secteur s: secteurs){
			nomSecteur = s.getNom();
			nomsSecteurs.add(nomSecteur);
		}
		
		
		ArrayList<String> nomsVoies = new ArrayList<String>();
		List<Voie> voies = new ArrayList<Voie>();
		for (Secteur s: secteurs) {
		voies = voieRepo.findBySecteur(s);
		String nomVoie = new String();
		for (Voie v: voies){
			nomVoie = v.getNom();
			System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuu");
			System.out.println(nomVoie);
			System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuu");
			nomsVoies.add(nomVoie);
			
		}
		
		}  
		
		
		
	}// fin chercherSite()

	

}
