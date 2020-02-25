package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Demande {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer id_topo;
	private Integer id_utilisateur;
	//private Integer idTopo;
	//private Integer idUtilisateur;
	
	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demande(Integer id, Integer id_topo, Integer id_utilisateur, Integer idTopo, Integer idUtilisateur) {
		super();
		this.id = id;
		this.id_topo = id_topo;
		this.id_utilisateur = id_utilisateur;
		//this.idTopo = idTopo;
		//this.idUtilisateur = idUtilisateur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdTopo() {
		return id_topo;
	}

	public void setIdTopo(Integer id_topo) {
		this.id_topo = id_topo;
	}

	public Integer getIdUtilisateur() {
		return id_utilisateur;
	}

	public void setIdUtilisateur(Integer id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
/**
	public Integer getIdTopo() {
		return idTopo;
	}

	public void setIdTopo(Integer idTopo) {
		this.idTopo = idTopo;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	*/
	
	
	
	
}
