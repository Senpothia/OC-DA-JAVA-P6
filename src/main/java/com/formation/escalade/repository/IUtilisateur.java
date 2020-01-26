package com.formation.escalade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.formation.escalade.model.Utilisateur;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface IUtilisateur extends JpaRepository <Utilisateur, Integer>{

	 Utilisateur findById(int id);
	
	
}
