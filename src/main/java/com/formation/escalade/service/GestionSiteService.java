package com.formation.escalade.service;

import java.util.Collection;

import com.formation.escalade.model.Site;

public interface GestionSiteService {
	
	   public abstract void createSite(Site site);
	   public abstract void updateSite(String id, Site site);
	   public abstract void deleteSite(String id);
	   public abstract Collection<Site> getSite();

}
