package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Voie;

public interface ISecteur extends JpaRepository <Secteur, Integer>{

	Secteur findByNom(String nom);

	List<Secteur> findBySite(Site site);

	Secteur findByNomIgnoreCase(String nom);



	
}
