package com.formation.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Commentaire_longueur;
import com.formation.escalade.model.Commentaire_site;

public interface ICommentaire_site extends JpaRepository <Commentaire_site, Integer>{
	
	

}
