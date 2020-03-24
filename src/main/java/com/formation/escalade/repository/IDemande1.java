package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Demande;

public interface IDemande1 extends JpaRepository<Demande, Integer> {

	Demande findByDemandeurIdAndTopoId(Integer idDemandeur, Integer idTopo);

	List<Demande> findByTopoId(Integer idTopo);

}
