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

	String[] listeTypes = { "Tous", "Officiel", "Autres" };
	List<String> types = Arrays.asList(listeTypes);

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

		Set<Site> sites = new LinkedHashSet<>(new ArrayList<Site>()); // liste à transmettre page html
		Set<Site> sites_copie = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sites_aux = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sites_parNom = new LinkedHashSet<>(new ArrayList<Site>());
		Set<Site> sites_parCreateur = new LinkedHashSet<>(new ArrayList<Site>());
		List<Site> sites_ParDepartement = new ArrayList<Site>();
		List<Site> sites_ParLocalisation = new ArrayList<Site>();
		List<Site> sites_Nbre_Secteurs = new ArrayList<Site>();
		/*
		 * try { // recherche par nom de site Site site =
		 * siteRepo.findByNomIgnoreCase(formSearch.getNom()); if (site != null) {
		 * 
		 * sites.add(site); }
		 * 
		 * } catch (NullPointerException e) {
		 * 
		 * System.out.println("aucune réponse"); }
		 * 
		 * try { // recherche par nom de secteur Secteur secteur =
		 * secteurRepo.findByNomIgnoreCase(formSearch.getNom());
		 * sites.add(secteur.getSite()); } catch (NullPointerException e) { } try { //
		 * recherche par nom de voie
		 * 
		 * Voie voie = voieRepo.findByNomIgnoreCase(formSearch.getNom()); Secteur
		 * secteur1 = voie.getSecteur(); sites.add(secteur1.getSite()); } catch
		 * (NullPointerException e) { }
		 * 
		 * try { // recherche par nom de longueur
		 * 
		 * Longueur longueur = longueurRepo.findByNomIgnoreCase(formSearch.getNom());
		 * Voie voie1 = longueur.getVoie(); Secteur secteur2 = voie1.getSecteur();
		 * sites.add(secteur2.getSite()); } catch (NullPointerException e) { }
		 * 
		 * // Recherche par nom de createur
		 * 
		 * try {
		 * 
		 * List<Utilisateur> createurs =
		 * utilisateurRepo.findByNomOrPrenomIgnoreCase(formSearch.getCreateur(),
		 * formSearch.getCreateur()); for (Utilisateur createur : createurs) {
		 * 
		 * Integer id = createur.getId(); List<Site> sites1 =
		 * siteRepo.findByCreateur(id); sites.addAll(sites1); }
		 * 
		 * } catch (NullPointerException e) { }
		 * 
		 * // Tri selon le département
		 * 
		 * if (formSearch.getDepartement() != 0) {
		 * 
		 * if (sites.isEmpty()) {
		 * 
		 * System.out.println("liste sites vide, recherche par département");
		 * sites.addAll(siteRepo.findByDepartement(formSearch.getDepartement()));
		 * 
		 * } else {
		 * 
		 * System.out.println("liste sites non vide, recherche par département");
		 * sites_copie.addAll(sites);
		 * 
		 * if (formSearch.getNom() != "" || formSearch.getCreateur() != "" ||
		 * formSearch.getLocalisation() != "") {
		 * 
		 * for (Site site : sites_copie) {
		 * 
		 * Integer departement = site.getDepartement(); if (departement !=
		 * formSearch.getDepartement()) {
		 * 
		 * sites.remove(site); }
		 * 
		 * sites_aux.addAll(siteRepo.findByDepartement(formSearch.getDepartement()));
		 * sites.addAll(sites_aux); }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * // Tri selon officiel if (formSearch.getNom().equals("")) {
		 * 
		 * String typeChoisi = formSearch.getType(); System.out.println("Type choisi: "
		 * + typeChoisi); System.out.println("Test officiel: " +
		 * typeChoisi.equals("Officiel")); sites_copie.addAll(sites); if
		 * (!typeChoisi.equals("Tous")) { for (Site site : sites_copie) {
		 * 
		 * Boolean status = site.isOfficiel();
		 * 
		 * if (typeChoisi.equals("Officiel") && !status) {
		 * 
		 * sites.remove(site); }
		 * 
		 * if (typeChoisi.equals("Autres") && status) {
		 * 
		 * sites.remove(site); }
		 * 
		 * }
		 * 
		 * } }
		 */

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

			List<Site> sites_n_secteur = new ArrayList<Site>();
			sites_n_secteur = siteRepo.findAll();
			System.out.println("Nbre de sites total en base: " + sites_n_secteur.size());
			if (sites_n_secteur != null) {

				for (Site site : sites_n_secteur) {

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

		//////////////////
		
		sites.addAll(sites_aux);
		sites.addAll(sites_parNom);
		sites.addAll(sites_Nbre_Secteurs);
		return sites;
	}

}
