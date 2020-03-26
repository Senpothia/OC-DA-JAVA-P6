package com.formation.escalade.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.Element;
import com.formation.escalade.model.FormSite;
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
public class SiteService implements GestionSiteService {

	@Autowired
	SiteService siteService;
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

	public SiteService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	@Override
	public Boolean createSite(FormSite formSite, Utilisateur utilisateur) {

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

		site.setCreateur(utilisateur.getId()); // Affectation du créateur du site

		try {

			siteRepo.save(site);

		} catch (Exception e) {

			return false;
		}

		Commentaire commentaire = new Commentaire();
		Utilisateur auteur = new Utilisateur();
		auteur.setId(1);
		commentaire.setAuteur(auteur);
		commentaire.setSite(site);
		commentaire.setText(remSite);

		try {

			commentaireRepo.save(commentaire);

		} catch (Exception e) {

			siteRepo.delete(site);
			return false;
		}

		Secteur secteur = new Secteur();
		secteur.setNom(nomSecteur);
		secteur.setSite(site);

		try {

			secteurRepo.save(secteur);

		} catch (Exception e) {

			siteRepo.delete(site);
			commentaireRepo.delete(commentaire);
			return false;
		}

		Voie voie = new Voie();
		voie.setNom(nomVoie);
		voie.setCotation(cotationVoie);
		voie.setSecteur(secteur);

		try {

			voieRepo.save(voie);

		} catch (Exception e) {

			siteRepo.delete(site);
			commentaireRepo.delete(commentaire);
			secteurRepo.delete(secteur);
			return false;
		}

		Longueur longueur = new Longueur();
		longueur.setNom(nomLongueur);
		longueur.setSpit(nbreSpit);
		longueur.setCotation(cotationLongueur);
		longueur.setVoie(voie);

		try {

			longueurRepo.save(longueur);

		} catch (Exception e) {

			siteRepo.delete(site);
			commentaireRepo.delete(commentaire);
			secteurRepo.delete(secteur);
			voieRepo.delete(voie);
			return false;
		}

		return true;
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
	public List<LigneSite> chercherSite(Integer id) {

		Site site = siteRepo.getOne(id);

		List<LigneSite> tableSite = new ArrayList<LigneSite>();
		String nomSite = new String();
		String nomSecteur = new String();
		String nomVoie = new String();
		String nomLongueur = new String();

		nomSite = site.getNom();

		List<Secteur> secteurs = secteurRepo.findBySite(site);
		List<Voie> voies = new ArrayList<Voie>();
		List<Longueur> longueurs = new ArrayList<Longueur>();

		ArrayList<String> nomsVoies = new ArrayList<String>();
		ArrayList<String> nomsSecteurs = new ArrayList<String>();
		ArrayList<String> nomsLongueurs = new ArrayList<String>();

		for (Secteur s : secteurs) {
			nomSecteur = s.getNom();
			voies = voieRepo.findBySecteur(s);
			for (Voie v : voies) {
				nomVoie = v.getNom();
				longueurs = longueurRepo.findByVoie(v);
				for (Longueur l : longueurs) {
					nomLongueur = l.getNom();
					LigneSite ligneSite = new LigneSite(nomSite, nomSecteur, nomVoie, nomLongueur);
					tableSite.add(ligneSite);
					System.out.println("*** ligne: " + ligneSite.toString());
				}
			}
		}

		return tableSite;

	}

	@Override
	public List<String> ordonnerSecteur(List<LigneSite> tableSite) {

		// String nomSite = new String();
		// nomSite = tableSite.get(0).getNomSite();
		String nomSecteur = new String("");

		List<String> nomsSecteurs = new ArrayList<String>();
		for (int i = 0; i <= tableSite.size() - 1; i++) {

			String nSecteur = tableSite.get(i).getNomSecteur();
			if (!nSecteur.equals(nomSecteur)) {

				nomsSecteurs.add(nSecteur);
				nomSecteur = nSecteur;
			}
		}
		/**
		 * for (String s: nomsSecteurs ) {
		 * 
		 * System.out.println("SECTEUR: "+ s); }
		 */

		return nomsSecteurs;
	}

	public List<String> ordonnerVoie(List<LigneSite> tableSite) {

		String nomSecteur = new String("");

		String nomVoie = new String("");

		// String nomLongueur = new String("");

		List<String> nomsSecteurs = new ArrayList<String>();
		for (int i = 0; i <= tableSite.size() - 1; i++) {

			String nSecteur = tableSite.get(i).getNomSecteur();
			if (!nSecteur.equals(nomSecteur)) {

				nomsSecteurs.add(nSecteur);
				nomSecteur = nSecteur;
			}
		}

		return null;
	}

	public Boolean[] decomposerSite(int id) {
		
		System.out.println("*** Entrée décomposerSite() *** ");
		Boolean [] arePresent = {false, false, false};
		
		Site site = siteRepo.getOne(id);
		// Traitement des secteurs
		List<Secteur> secteurs = secteurRepo.findBySite(site); // Récupération de tous les secteurs du site
		
	
		arePresent[0]=secteurs.isEmpty();
		System.out.println("presence secteur: " + arePresent[0]);
		// Traitement des voies

		List<Voie> listeVoies = new ArrayList<Voie>();

		for (Secteur s : secteurs) {

			List<Voie> voies = new ArrayList<Voie>();
			voies = voieRepo.findBySecteur(s); // Récupération de toutes les voies d'un secteur
			listeVoies.addAll(voies); // Une liste de voie par secteur dans une liste globale de voie (listeVoies)
		}
		
		
		arePresent[1] = listeVoies.isEmpty();
		System.out.println("presence voie: " + arePresent[1]);
		// Traitement des longueurs

		List<Longueur> listeLongueurs = new ArrayList<Longueur>();

		for (Voie v : listeVoies)

		{
			List<Longueur> longueurs = new ArrayList<Longueur>();
			longueurs = longueurRepo.findByVoie(v);
			listeLongueurs.addAll(longueurs);

		}
		
		
		arePresent[2] = listeLongueurs.isEmpty();
		System.out.println("presence longueur: " + arePresent[2]);
		
		return arePresent;
	}

	// **************************************************

	public List<String> decomposerSecteur(int id) {

		Site site = siteRepo.getOne(id);
		// Traitement des secteurs
		List<Secteur> secteurs = secteurRepo.findBySite(site); // Récupération de tous les secteurs du site
		String nomSecteur = new String();
		List<String> nomSecteurs = new ArrayList<String>();
		for (Secteur s : secteurs) { // Affichage de chaque secteur

			System.out.println(s.toString());
		}

		for (Secteur s : secteurs) {

			nomSecteur = s.getNom();
			nomSecteurs.add(nomSecteur);
		}

		return nomSecteurs;

	}///

	public List<List<String>> decomposerVoie(List<Secteur> secteurs) {

		List<Voie> voies = new ArrayList<Voie>();
		List<List<Voie>> listeVoies = new ArrayList<List<Voie>>();
		String nomVoie = new String();
		List<String> nomVoiesSecteur = new ArrayList<String>();
		List<List<String>> listeNomsVoies = new ArrayList<List<String>>();
		for (Secteur s : secteurs) {
			voies = voieRepo.findBySecteur(s); // Récupération de toutes les voies d'un secteur
			listeVoies.add(voies); // Une liste de voie par secteur dans une liste globale de voie (listeVoies)
		}

		for (List<Voie> v : listeVoies) {
			for (Voie w : v) {
				System.out.println(w.toString()); // Affichage de toutes les voies
				nomVoie = w.getNom();
				nomVoiesSecteur.add(nomVoie);
			}
			listeNomsVoies.add(nomVoiesSecteur);
		}

		return listeNomsVoies;
	}//

	public List<List<String>> decomposerLongueur(List<List<Voie>> listeVoies) {

		List<Longueur> longueurs = new ArrayList<Longueur>();
		List<List<Longueur>> listeLongueurs = new ArrayList<List<Longueur>>();
		String nomLongueur = new String();
		List<String> nomLongueursVoie = new ArrayList<String>();
		List<List<String>> listeNomsLongueurs = new ArrayList<List<String>>();

		for (List<Voie> v : listeVoies) {

			for (Voie w : v) {
				longueurs = longueurRepo.findByVoie(w);
				listeLongueurs.add(longueurs);

			}
		}

		for (List<Longueur> l : listeLongueurs) {
			for (Longueur j : l) {
				System.out.println(j.toString()); // Affichage de toutes les voies
				nomLongueur = j.getNom();
				nomLongueursVoie.add(nomLongueur);
			}
			listeNomsLongueurs.add(nomLongueursVoie);
		}

		return listeNomsLongueurs;
	}

	// **********************************
	public List<Secteur> chercherSecteurs(int id) {

		Site site = siteRepo.getOne(id);
		List<Secteur> secteurs = secteurRepo.findBySite(site);

		return secteurs;
	}

	public List<List<Voie>> chercherVoies(List<Secteur> secteurs) {

		List<Voie> voies = new ArrayList<Voie>();
		List<List<Voie>> listeVoies = new ArrayList<List<Voie>>();

		for (Secteur s : secteurs) {
			voies = voieRepo.findBySecteur(s); // Récupération de toutes les voies d'un secteur
			listeVoies.add(voies); // Une liste de voie par secteur dans une liste globale de voie (listeVoies)
		}

		return listeVoies;

	} //

	public List<List<Longueur>> chercherLongueurs(List<List<Voie>> listeVoies) {

		List<Longueur> longueurs = new ArrayList<Longueur>();
		List<List<Longueur>> listeLongueurs = new ArrayList<List<Longueur>>();

		for (List<Voie> v : listeVoies) {

			for (Voie w : v) {
				longueurs = longueurRepo.findByVoie(w);
				listeLongueurs.add(longueurs);
			}
		}
		return listeLongueurs;
	}

}// ***