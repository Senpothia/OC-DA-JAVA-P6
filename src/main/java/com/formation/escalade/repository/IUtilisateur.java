package com.formation.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.formation.escalade.model.Utilisateur;

@Component
public interface IUtilisateur extends JpaRepository <Utilisateur, Integer>{

}
