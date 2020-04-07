package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Voie;

public interface ISecteur extends JpaRepository <Secteur, Integer>{

	Secteur findByNom(String nom);

	List<Secteur> findBySite(Site site);

	Secteur findByNomIgnoreCase(String nom);

	@Query(value = "select * from secteur u where u.nom like ?1", nativeQuery = true)
	List<Secteur> findAllSecteurStartBy(String string);


	
}
