package com.formation.escalade.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.GroupeGal;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IVoie;


@Service
public class GeneralService{

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
	private final ICommentaire commentaireRepo;

	/**
	 * private Site site; private List<Secteur> secteurs; private List<Voie> voies;
	 * private List<Longueur> longueurs;
	 * 
	 */
	final int LIGNE = 5; // nombre de site afficher par ligne de la galerie

	public GeneralService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}
  
   public void pagination(int page, Model model){
   
		List<String> nomsSites = new ArrayList<>();
		List<Boolean> tags = new ArrayList<>();
		List<GroupeGal> groupes = new ArrayList<>();
		List<Site> sites = siteRepo.findAll();
		for (Site site : sites) {
			nomsSites.add(site.getNom());
			tags.add(site.isOfficiel());
			GroupeGal groupe = new GroupeGal();
			groupe.setNomSite(site.getNom());
			groupe.setOfficiel(site.isOfficiel());
			System.out.println(groupe.toString());
			groupes.add(groupe);
		}
		
		int tailleGroupes = groupes.size();
		int taille = nomsSites.size(); // nombre de sites enregistrés
		// taille = taille + 29; // test calcul nbre de pages
		System.out.println("nbre de sites: " + taille);
		System.out.println("nbre de groupes: " + tailleGroupes);
		int nbrePages = taille / (2 * LIGNE); // nbre de pages pleines
		int reste = taille % (2 * LIGNE); // nbre de sites contenus dans page incomplète

		if (reste != 0) { // il reste des sites en plus deceux sur les pages pleines

			nbrePages++; // page suplémentaire pour les sites restants
		}

		System.out.println("nbre de pages: " + nbrePages);
		System.out.println("reste: " + reste);

		List<String> ligne1 = new ArrayList<>();
		List<Boolean> tagsLigne1 = new ArrayList<>();
		List<GroupeGal> groupesLigne1 = new ArrayList<>();
		List<String> ligne2 = new ArrayList<>();
		List<Boolean> tagsLigne2 = new ArrayList<>();
		List<GroupeGal> groupesLigne2 = new ArrayList<>();
		
		int borneInf = (page - 1) * 2 * LIGNE;
		int borneSup = borneInf + LIGNE - 1;

		if (page == nbrePages) { // traitement de la dernière page

			System.out.println("Traitement derniere page ");

			if (reste < LIGNE) { // Une seule ligne à remplir

				for (int i = borneInf; i < borneInf + reste; i++) {
					System.out.println("indice ligne partielle unique derniere page: " + i);
					ligne1.add(nomsSites.get(i)); // determiner l'indice i
					tagsLigne1.add(tags.get(i));
					groupesLigne1.add(groupes.get(i));
				}

			} else {		// Deux lignes à remplir
				for (int i = borneInf; i < borneSup + 1; i++) { // Remplissage ligne 1 normalement
					System.out.println("indice ligne pleine deniere page: " + i);
					ligne1.add(nomsSites.get(i)); // determiner l'indice i
					tagsLigne1.add(tags.get(i));
					groupesLigne1.add(groupes.get(i));
				}

				for (int i = borneInf + LIGNE; i < borneInf + LIGNE +(reste - LIGNE); i++) { // Remplissage ligne 2 partiellement
					System.out.println("indice ligne partielle derniere page: " + i);
					ligne2.add(nomsSites.get(i)); // determiner l'indice i
					tagsLigne2.add(tags.get(i));
					groupesLigne2.add(groupes.get(i));
				}
			}
			
			model.addAttribute("ligne1", ligne1);
			model.addAttribute("tagsLigne1", tagsLigne1);
			model.addAttribute("groupesLigne1", groupesLigne1);
			model.addAttribute("ligne2", ligne2);
			model.addAttribute("tagsLigne2", tagsLigne2);
			model.addAttribute("groupesLigne2", groupesLigne2);
			
		} // fin traitement de la dernière page

		if (page < nbrePages || reste == 0) { // traitement page pleine

			for (int i = borneInf; i < borneSup + 1; i++) { // Remplissage lignes pleines - ligne 1
				System.out.println("indice ligne pleine: " + i);
				ligne1.add(nomsSites.get(i));
				tagsLigne1.add(tags.get(i));
				groupesLigne1.add(groupes.get(i));
			}

			model.addAttribute("ligne1", ligne1);
			model.addAttribute("tagsLigne1", tagsLigne1);
			model.addAttribute("groupesLigne1", groupesLigne1);

			for (int i = borneInf + LIGNE; i < borneSup + LIGNE + 1; i++) { // Remplissage lignes pleines - ligne 2
				System.out.println("indice ligne pleine: " + i);
				ligne2.add(nomsSites.get(i));
				tagsLigne2.add(tags.get(i));
				groupesLigne2.add(groupes.get(i));
			}

			model.addAttribute("ligne2", ligne2);
			model.addAttribute("tagsLigne2", tagsLigne2);
			model.addAttribute("groupesLigne2", groupesLigne2);
			
		} // fin traitement ligne pleine

		List<String> numPages = new ArrayList<>();
		for (int i = 1; i < nbrePages + 1; i++) { // Définition des numéro de page pour thymeleaf
			String numPage = String.valueOf(i);
			System.out.println("num de page thymeleaf pour pagination : " + numPage);
			numPages.add(numPage);

		}
		String previous = String.valueOf(page == 1 ? 1 : page - 1); // Détermination des numéro de pages
		String next = String.valueOf(page == nbrePages ? nbrePages : page + 1);// pour les boutons previous et next
		model.addAttribute("numPages", numPages);
		model.addAttribute("previous", previous);
		model.addAttribute("next", next);

		for (int i = 0; i < ligne1.size(); i++) {

			System.out.println("ligne 1: " + i +" " + ligne1.get(i));
		}

		for (int i = 0; i < ligne2.size(); i++) {

			System.out.println("ligne 2: " + i +" "+ ligne2.get(i));
		}
   
   }
 

}
