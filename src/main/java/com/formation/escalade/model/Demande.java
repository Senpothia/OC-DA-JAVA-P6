package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Entity
public class Demande {

	@Id
	@GeneratedValue
	private Integer id;
	
	//private Integer id_topo;
	//private Integer id_utilisateur;
	//private Integer idTopo;
	//private Integer idUtilisateur;
	
	@ManyToOne
	private Utilisateur demandeur;
	
	@ManyToOne
	private Topo topo;

	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demande(Integer id, Utilisateur demandeur, Topo topo) {
		super();
		this.id = id;
		this.demandeur = demandeur;
		this.topo = topo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Utilisateur demandeur) {
		this.demandeur = demandeur;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}


}
