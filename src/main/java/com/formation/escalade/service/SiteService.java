package com.formation.escalade.service;

import java.util.Collection;

import com.formation.escalade.model.Site;

@Service
public class SiteService implements GestionSiteService {
	
	

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

		longueurRepo.save(longueur)
		

	}

	@Override
	public void updateSite(String id, Site site) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSite(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Site> getSite() {
		// TODO Auto-generated method stub
		return null;
	}

}
