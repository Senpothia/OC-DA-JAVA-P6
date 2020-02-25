package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Demande {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer id_topo;
	private Integer id_utilisateur;
	//private Integer idTopo;
	//private Integer idUtilisateur;
	
	@ManyToMany(mappedBy = "demandes")
	List<Utilisateur> utilisateurs;

	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demande(Integer id, Integer id_topo, Integer id_utilisateur, List<Utilisateur> utilisateurs) {
		super();
		this.id = id;
		this.id_topo = id_topo;
		this.id_utilisateur = id_utilisateur;
		this.utilisateurs = utilisateurs;
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

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	

	
}
