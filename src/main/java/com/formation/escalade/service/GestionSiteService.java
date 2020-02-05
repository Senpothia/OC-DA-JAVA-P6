package com.formation.escalade.service;

import java.util.Collection;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Site;

public interface GestionSiteService {
	
	   public abstract void createSite(Site site);
	   public abstract void updateSite(Integer id, Site site);
	   public abstract void deleteSite(Integer id);
	   public abstract Collection<Site> getSite();
	   void createSite(FormSite formSite);

}
