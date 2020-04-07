package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Demande;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Topo;


public interface ITopo extends JpaRepository <Topo, Integer>{

	List<Topo> findBySite(Site site);

	Topo findByNom(String string);

	List<Topo> findByDescription(String phrase);

	List<Topo> findByLieu(String phrase);

	List<Topo> findByDisponible(boolean b);

	List<Topo> findAllTopoStartBy(String phrase1);

	
}
