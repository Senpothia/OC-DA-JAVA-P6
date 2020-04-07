package com.formation.escalade.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;

public interface ISite extends JpaRepository <Site, Integer>{

	

	Site findByNom(String nomSite);

	List<Site> findByLocalisation(String phrase);

	List<Site> findByCreateur(Integer id);

	Site findByNomIgnoreCase(String nom);

	List<Site> findByDepartement(Integer departement);

	List<Site> findByLocalisationIgnoreCase(String localisation);

	@Query(value = "select * from site u where u.nom like ?1 or u.localisation like ?1", nativeQuery = true)
	List<Site> findAllSiteStartBy(String string);

	
	
	

}
