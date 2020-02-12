package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;

public interface ISite extends JpaRepository <Site, Integer>{

	

	Site findByNom(String nomSite);

	

	
	
	

}
