package com.formation.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Profil;

public interface IProfil extends JpaRepository <Profil, Integer> {

}
