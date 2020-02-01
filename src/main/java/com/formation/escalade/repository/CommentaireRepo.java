package com.formation.escalade.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Commentaire;

public interface CommentaireRepo extends JpaRepository<Commentaire, Integer> {

}
