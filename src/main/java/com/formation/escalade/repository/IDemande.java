package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.formation.escalade.model.Demande;

public interface IDemande extends JpaRepository <Demande, Integer>{

	List<Demande> findByIdUtilisateur(Integer id);

	//List<Demande> findByIdUtilisateur(Integer id_utilisateur);

}
