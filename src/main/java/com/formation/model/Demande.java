package com.formation.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Demande {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer id_topo;
	private Integer id_utilisateur;
	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Demande(Integer id, Integer id_topo, Integer id_utilisateur) {
		super();
		this.id = id;
		this.id_topo = id_topo;
		this.id_utilisateur = id_utilisateur;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_topo() {
		return id_topo;
	}
	public void setId_topo(Integer id_topo) {
		this.id_topo = id_topo;
	}
	public Integer getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(Integer id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	
	
}
