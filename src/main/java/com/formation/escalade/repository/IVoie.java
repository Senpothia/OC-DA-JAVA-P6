package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Voie;

public interface IVoie extends JpaRepository <Voie, Integer>{

	List<Voie> findBySecteur(Secteur s);

	Voie findByNom(String nomVoie);

	List<Voie> findByCotation(String phrase);

	//<Secteur> IVoie findAllBySecteurs(List<Secteur> secteurs);

}
