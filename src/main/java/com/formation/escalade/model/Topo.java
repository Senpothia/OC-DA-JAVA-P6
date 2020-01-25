package com.formation.escalade.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Topo {

	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String description;
	private String lieux;
	private Date date;
	private boolean disponible;
	private Integer id_utilisateur;
	private Integer id_site;
	public Topo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Topo(Integer id, String nom, String description, String lieux, Date date, boolean disponible,
			Integer id_utilisateur, Integer id_site) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.lieux = lieux;
		this.date = date;
		this.disponible = disponible;
		this.id_utilisateur = id_utilisateur;
		this.id_site = id_site;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLieux() {
		return lieux;
	}
	public void setLieux(String lieux) {
		this.lieux = lieux;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Integer getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(Integer id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public Integer getId_site() {
		return id_site;
	}
	public void setId_site(Integer id_site) {
		this.id_site = id_site;
	}
	
	
}
