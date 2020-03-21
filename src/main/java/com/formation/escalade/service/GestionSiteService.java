package com.formation.escalade.service;

import java.util.Collection;
import java.util.List;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;

public interface GestionSiteService {
	
	   public abstract void createSite(Site site);
	   public abstract void updateSite(Integer id, Site site);
	   public abstract void deleteSite(Integer id);
	   public abstract Collection<Site> getSite();
	   void createSite(FormSite formSite, Utilisateur utilisateur);
	   List<LigneSite> chercherSite(Integer id);
	   abstract List<String> ordonnerSecteur(List<LigneSite> tableSite);
	   abstract List<String> ordonnerVoie(List<LigneSite> tableSite);
	  

}
