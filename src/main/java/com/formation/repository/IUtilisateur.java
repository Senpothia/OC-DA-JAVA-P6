package com.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.model.Utilisateur;

public interface IUtilisateur extends JpaRepository <Utilisateur, Integer>{

}
