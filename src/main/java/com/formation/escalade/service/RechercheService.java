package com.formation.escalade.service;

import java.util.ArrayList;
import java.util.Arrays;
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

	private final ITopo topoRepo;

	String[] listeTypes = { "Tous", "Officiel", "Autres" };
	List<String> types = Arrays.asList(listeTypes);

	public RechercheService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo, ITopo topoRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
		this.topoRepo = topoRepo;
	}

	public List<Utilisateur> rechercheCreateur(Set<Site> sites) {

		List<Utilisateur> createurs = new ArrayList<Utilisateur>();

		for (Site site : sites) {

			Integer idCreateur = site.getCreateur();
			Utilisateur createur = utilisateurRepo.getOne(idCreateur);
			createurs.add(createur);
		}

		System.out.println("taille de la liste createur, rechercheCreateur dans service: " + sites.size());
		return createurs;
	}

	public Set<Site> recherche(FormSearch formSearch) {

		List<Site> tousLesSites = new ArrayList<Site>();
		tousLesSites = siteRepo.findAll();
		Set<Site> sites = new LinkedHashSet<>(new ArrayList<Site>()); // liste à transmettre page html
		Set<Site> sites_copie = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sites_aux = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sites_parNom = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sites_parCreateur = new LinkedHashSet<>(new ArrayList<Site>());
		List<Site> sites_ParDepartement = new ArrayList<Site>();
		List<Site> sites_ParLocalisation = new ArrayList<Site>();
		List<Site> sites_Nbre_Secteurs = new ArrayList<Site>();
		List<Site> sites_Nbre_Voies = new ArrayList<Site>();
		List<Site> sites_Nbre_Longueurs = new ArrayList<Site>();
		List<Site> sites_Nbre_Spits = new ArrayList<Site>();
		List<Site> sites_Nbre_Topos = new ArrayList<Site>();
		List<Site> sites_Nbre_Topos_dispo = new ArrayList<Site>();
		List<Site> sites_Par_Cotation = new ArrayList<Site>();

		// Recherche par nom de site, secteur, voie, longueur

		if (formSearch.getNom() != "") {

			Site site = siteRepo.findByNomIgnoreCase(formSearch.getNom());
			if (site != null) {
				sites_parNom.add(site);

			}

			Secteur secteur = secteurRepo.findByNomIgnoreCase(formSearch.getNom());
			if (secteur != null) {

				Site site_nom_secteur = secteur.getSite();
				sites_parNom.add(site_nom_secteur);
			}

			Voie voie = voieRepo.findByNomIgnoreCase(formSearch.getNom());
			if (voie != null) {

				Secteur secteur_voie = voie.getSecteur();
				Site site_voie = secteur_voie.getSite();
				sites_parNom.add(site_voie);
			}

			Longueur longueur = longueurRepo.findByNomIgnoreCase(formSearch.getNom());
			if (longueur != null) {
				Voie voie_longueur = longueur.getVoie();
				Secteur secteur_longueur = voie_longueur.getSecteur();
				Site site_longueur = secteur_longueur.getSite();
				sites_parNom.add(site_longueur);
			}

		}

		// Recherche par nom de createur

		if (formSearch.getCreateur() != "") {

			try {

				List<Utilisateur> createurs = utilisateurRepo.findByNomOrPrenomIgnoreCase(formSearch.getCreateur(),
						formSearch.getCreateur());
				for (Utilisateur createur : createurs) {

					Integer id = createur.getId();
					List<Site> sites1 = siteRepo.findByCreateur(id);
					sites_parCreateur.addAll(sites1);
				}

				sites_aux.addAll(sites_parCreateur);

			} catch (NullPointerException e) {
			}

		}

		// Recherche par département

		if (formSearch.getDepartement() != 0) {

			sites_ParDepartement = siteRepo.findByDepartement(formSearch.getDepartement());

			if (sites_ParDepartement != null) {

				sites_aux.addAll(sites_ParDepartement);
			}
		}

		// Recherche par localisation

		if (formSearch.getLocalisation() != "") {

			sites_ParLocalisation = siteRepo.findByLocalisationIgnoreCase(formSearch.getLocalisation());

			if (sites_ParLocalisation != null) {

				sites_aux.addAll(sites_ParLocalisation);
			}
		}

		// Tri selon officiel

		String typeChoisi = formSearch.getType();
		System.out.println("Type choisi: " + typeChoisi);
		System.out.println("Test officiel: " + typeChoisi.equals("Officiel"));
		sites_copie.addAll(sites_aux);
		if (!typeChoisi.equals("Tous")) {
			for (Site site : sites_copie) {

				Boolean status = site.isOfficiel();

				if (typeChoisi.equals("Officiel") && !status) {

					sites_aux.remove(site);
				}

				if (typeChoisi.equals("Autres") && status) {

					sites_aux.remove(site);
				}

			}

		}

		// Recherche par nombre de secteurs

		int nbreSecteur = formSearch.getSecteurs();
		String secteur_crit = formSearch.getSecteurs_crit();

		System.out.println("Critère nbre de secteur: " + nbreSecteur);
		System.out.println("Critère de qte sur secteur: " + secteur_crit);

		if (nbreSecteur != 0) {

			System.out.println("Nbre de sites total en base: " + tousLesSites.size());
			if (tousLesSites != null) {

				for (Site site : tousLesSites) {

					List<Secteur> secteurs = site.getSecteurs();
					if (secteurs.size() == nbreSecteur && secteur_crit.equals("Egal")) {

						sites_Nbre_Secteurs.add(site);
					}

					if (secteurs.size() < nbreSecteur && secteur_crit.equals("Moins")) {

						sites_Nbre_Secteurs.add(site);
					}

					if (secteurs.size() > nbreSecteur && secteur_crit.equals("Plus")) {

						sites_Nbre_Secteurs.add(site);
					}
				}
			}

		}

		// Recherche par nombre de voies

		int nbreVoies = formSearch.getVoies(); // critère de nbre de voie reçu
		String voie_crit = formSearch.getVoies_crit(); // critère de qté

		System.out.println("Critère nbre de voie: " + nbreVoies);
		System.out.println("Critère de qte sur voie: " + voie_crit);
		System.out.println("Nbre total de site en bdd: " + tousLesSites.size());
		int nbreVoiesComptees = 0;
		int nbreVoiesListees = 0;
		if (nbreVoies != 0) {
			System.out.println("***************");

			for (Site site : tousLesSites) {
				nbreVoiesComptees = 0;
				nbreVoiesListees = 0;
				List<Secteur> secteurs = site.getSecteurs();
				if (!secteurs.isEmpty()) {
					System.out.println("sssssssssssss");
					for (Secteur secteur : secteurs) {
						nbreVoiesListees = 0;
						List<Voie> voies = secteur.getVoies();
						nbreVoiesListees = voies.size();
						nbreVoiesComptees = nbreVoiesComptees + nbreVoiesListees;
					}
				}

				System.out.println("NbreVoieComptees = " + nbreVoiesComptees);
				if (nbreVoiesComptees == nbreVoies && voie_crit.equals("Egal")) {

					System.out.println("=======");
					sites_Nbre_Voies.add(site);
				}

				if (nbreVoiesComptees < nbreVoies && voie_crit.equals("Moins")) {

					System.out.println("--------");
					sites_Nbre_Voies.add(site);
				}

				if (nbreVoiesComptees > nbreVoies && voie_crit.equals("Plus")) {

					System.out.println("++++++++");
					sites_Nbre_Voies.add(site);
				}
			}
			System.out.println("Taille liste de site pour recherche par voie: " + sites_Nbre_Voies.size());
		}

		// Recherche par nombre de longueur
		System.out.println("llllllllllllllllllllllllllll");
		int nbreLongueurs = formSearch.getLongueurs(); // critère de nbre de longueur reçu
		String longueur_crit = formSearch.getLongueurs_crit(); // critère de qté

		System.out.println("Critère nbre de longueurs: " + nbreLongueurs);
		System.out.println("Critère de qte sur voie: " + longueur_crit);
		System.out.println("Nbre total de site en bdd: " + tousLesSites.size());

		int nbreLongueursComptees = 0;
		int nbreLongueursListees = 0;
		
		if (nbreLongueurs != 0) {
			System.out.println("llllllllllllllllllllllllllll");
			for (Site site : tousLesSites) {

				nbreLongueursComptees = 0;
				nbreLongueursListees = 0;

				List<Secteur> secteurs = site.getSecteurs();
				for (Secteur secteur : secteurs) {

					List<Voie> voies = secteur.getVoies();
					for (Voie voie : voies) {

						List<Longueur> longueurs = voie.getLongueurs();
						nbreLongueursListees = longueurs.size();
						nbreLongueursComptees = nbreLongueursComptees + nbreLongueursListees;
						System.out.println("NbreVoieComptees inter boucle= " + nbreLongueursComptees);
					}

				}

				System.out.println("NbreLongueursComptees = " + nbreLongueursComptees);
				if (nbreLongueursComptees == nbreLongueurs && longueur_crit.equals("Egal")) {

					System.out.println("++++++++");
					sites_Nbre_Longueurs.add(site);
				}

				if (nbreLongueursComptees < nbreLongueurs && longueur_crit.equals("Moins")) {

					System.out.println("++++++++");
					sites_Nbre_Longueurs.add(site);
				}

				if (nbreLongueursComptees > nbreLongueurs && longueur_crit.equals("Plus")) {

					System.out.println("++++++++");
					sites_Nbre_Longueurs.add(site);
				}

			}

		}
		// Recherche par nombre de spits

		System.out.println("ppppppppppppppppppppppppp");

		int nbreSpits = formSearch.getSpits(); // critère de nbre de spits reçu
		String spits_crit = formSearch.getSpits_crit(); // critère de qté

		if (nbreSpits != 0) {

			if (spits_crit.equals("Egal")) {

				List<Longueur> longueurs = longueurRepo.findBySpit(nbreSpits);
				if (longueurs != null) {

					for (Longueur longueur : longueurs) {

						Voie voie = longueur.getVoie();
						Secteur secteur = voie.getSecteur();
						Site site = secteur.getSite();
						sites_Nbre_Spits.add(site);
					}
				}

			}

			if (spits_crit.equals("Moins")) {

				List<Longueur> longueurs = longueurRepo.findBySpitLessThan(nbreSpits);
				for (Longueur longueur : longueurs) {

					Voie voie = longueur.getVoie();
					Secteur secteur = voie.getSecteur();
					Site site = secteur.getSite();
					sites_Nbre_Spits.add(site);
				}
			}

			if (spits_crit.equals("Plus")) {
				List<Longueur> longueurs = longueurRepo.findBySpitGreaterThan(nbreSpits);
				for (Longueur longueur : longueurs) {

					Voie voie = longueur.getVoie();
					Secteur secteur = voie.getSecteur();
					Site site = secteur.getSite();
					sites_Nbre_Spits.add(site);
				}
			}

		}
		// Recherche par nombre de topos

		System.out.println("tttttttttttttttttttt");

		int nbreTopos = formSearch.getTopos(); // critère de nbre de spits reçu
		String topos_crit = formSearch.getTopos_crit(); // critère de qté

		if (nbreTopos != 0) {

			for (Site site : tousLesSites) {

				List<Topo> topos = site.getTopos();
				int nbreTopoListee = topos.size();
				if (nbreTopoListee == nbreTopos && topos_crit.equals("Egal")) {

					sites_Nbre_Topos.add(site);
				}

				if (nbreTopoListee < nbreTopos && topos_crit.equals("Moins")) {

					sites_Nbre_Topos.add(site);
				}

				if (nbreTopoListee > nbreTopos && topos_crit.equals("Plus")) {

					sites_Nbre_Topos.add(site);
				}
			}

		}
		// Recherche par nombre de topos disponibles

		System.out.println("ddddddddddddddddddd");

		int nbreToposDisponibles = formSearch.getTopos_dispo(); // critère de nbre de spits reçu
		String topos_dispo_crit = formSearch.getTopos_dispo_crit();// critère de qté

		if (nbreToposDisponibles != 0) {
			int NbreToposDispos = 0;
			for (Site site : tousLesSites) {

				NbreToposDispos = 0;
				List<Topo> toposSite = site.getTopos();
				for (Topo topo : toposSite) {

					Boolean disponible = topo.isDisponible();
					if (disponible) {
						NbreToposDispos++;
					}
				}

				if (NbreToposDispos == nbreToposDisponibles && topos_dispo_crit.equals("Egal")) {

					sites_Nbre_Topos_dispo.add(site);
				}

				if (NbreToposDispos < nbreToposDisponibles && topos_dispo_crit.equals("Moins")) {

					sites_Nbre_Topos_dispo.add(site);
				}

				if (NbreToposDispos > nbreToposDisponibles && topos_dispo_crit.equals("Plus")) {

					sites_Nbre_Topos_dispo.add(site);
				}

			}
		}

		// Recherche par cotation
		System.out.println("cccccccccccccccccc");

		String cotation = formSearch.getCotation();
		System.out.println("Cotation récupérée: " + cotation);
		String cotation_crit = formSearch.getCotation_crit();
		System.out.println("Cotation critère récupérée: " + cotation_crit);
		Set<Site> sitesCotationsEgal = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sitesCotationsMoins = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sitesCotationsPlus = new LinkedHashSet<>(new ArrayList<Site>());
		
		String cot = new String();
		int resultCompare = 0;
		Integer id; 
		
		if (!cotation.equals("")) {

			System.out.println("-------------- Recherche par cotation -------------");
			
			List<Longueur> longueurs = longueurRepo.findAllLongueurByCotation(); 
			List<Voie> voies = voieRepo.findAllVoieByCotation();
			System.out.println("Taille liste longueurs: " + longueurs.size());
			System.out.println("Taille liste voies: " + voies.size());
			
			if (longueurs != null) {
				
				for (Longueur longueur : longueurs) {
					
					cot = longueur.getCotation();
					resultCompare = cotation.compareTo(cot);
					System.out.println("Result compare: " + resultCompare);
					if (resultCompare == 0) {
						
						 Site site = demandeSiteLong(longueur);
						 sitesCotationsEgal.add(site);
					}
					
					if (resultCompare > 0) {
						
						 Site site = demandeSiteLong(longueur);
						 sitesCotationsMoins.add(site);
					}
					
					if (resultCompare < 0) {
								
						 Site site = demandeSiteLong(longueur);
						 sitesCotationsPlus.add(site);
					}
					
				}
				
			}
			
			if (voies != null) {
				
				for (Voie voie : voies) {
					
					cot = voie.getCotation();
					resultCompare = cotation_crit.compareTo(cot);
					
					if (resultCompare == 0) {
									
						 Site site = demandeSiteVoie(voie);
						 sitesCotationsEgal.add(site);
					}
					
					if (resultCompare > 0) {
											
						 Site site = demandeSiteVoie(voie);
						 sitesCotationsMoins.add(site);
					}
					
					if (resultCompare < 0) {
							
						 Site site = demandeSiteVoie(voie);
						 sitesCotationsPlus.add(site);
					}
					
				}
			}
			
			if (cotation_crit.equals("Egal")){
				
				sites.addAll(sitesCotationsEgal);
			}
			
			if (cotation_crit.equals("Moins")){
				
				sites.addAll(sitesCotationsMoins);
			}
			
			if (cotation_crit.equals("Plus")){
				
				sites.addAll(sitesCotationsPlus);
			}
			
		}
		// Transmission liste

		sites.addAll(sites_aux);
		sites.addAll(sites_parNom);
		sites.addAll(sites_Nbre_Secteurs);
		sites.addAll(sites_Nbre_Voies);
		sites.addAll(sites_Nbre_Longueurs);
		sites.addAll(sites_Nbre_Spits);
		sites.addAll(sites_Nbre_Topos);
		sites.addAll(sites_Nbre_Topos_dispo);
		
		return sites;
	}
	
	public Site demandeSiteLong(Longueur longueur) {
		
		 Voie voie = longueur.getVoie();
		 Secteur secteur = voie.getSecteur();
		 Site site = secteur.getSite();
		 return site;
	}
	
	public Site demandeSiteVoie(Voie voie) {
		
		 Secteur secteur = voie.getSecteur();
		 Site site = secteur.getSite();
		 return site;
	}
}
