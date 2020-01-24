package com.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.model.Commentaire_longueur;
import com.formation.model.Commentaire_site;

public interface ICommentaire_site extends JpaRepository <Commentaire_site, Integer>{
	
	

}
