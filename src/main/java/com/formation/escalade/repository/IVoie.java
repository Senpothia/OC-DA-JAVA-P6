package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;

public interface IVoie extends JpaRepository <Voie, Integer>{

	List<Voie> findBySecteur(Secteur s);

	Voie findByNom(String nomVoie);

	List<Voie> findByCotation(String phrase);

	Voie findByNomIgnoreCase(String nom);

	@Query( value = "select * from voie order by cotation", nativeQuery = true)
	List<Voie> findAllVoieByCotation();
	
	@Query(value = "select * from voie u where u.nom like ?1", nativeQuery = true)
	List<Voie> findAllVoieStartBy(String string);
	
	@Query(value = "select * from voie u where u.nom like ?", nativeQuery = true)
	List<Voie> findAllVoieNomStartBy(String string);

}
