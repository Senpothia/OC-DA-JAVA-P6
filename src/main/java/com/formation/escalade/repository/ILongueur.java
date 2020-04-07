package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;

public interface ILongueur extends JpaRepository <Longueur, Integer> {

	List<Longueur> findByVoie(Voie s);

	Longueur findByNom(String nomLongueur);

	List<Longueur> findByCotation(String phrase);

	Longueur findByNomIgnoreCase(String nom);

	List<Longueur> findBySpit(int nbreSpits);

	List<Longueur> findBySpitLessThan(int nbreSpits);

	List<Longueur> findBySpitGreaterThan(int nbreSpits);

	@Query( value = "select * from longueur order by cotation", nativeQuery = true)
	List<Longueur> findAllLongueurByCotation();
	
	@Query(value = "select * from longueur u where u.nom like ?1", nativeQuery = true)
	List<Longueur> findAllLongueurStartBy(String string);
	
	
}
