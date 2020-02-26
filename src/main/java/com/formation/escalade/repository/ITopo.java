package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.formation.escalade.model.Topo;

public interface ITopo extends JpaRepository <Topo, Integer>{

	List<Topo> findByIdSite(Integer id_site);

	//List<Topo> findByIdSite(Integer id_site);

	//List<Topo> findByLieu(String string);

}
