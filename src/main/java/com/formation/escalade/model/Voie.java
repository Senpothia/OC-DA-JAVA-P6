package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Voie {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String cotation;
	private Integer id_secteur;
	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voie(Integer id, String nom, String cotation, Integer id_secteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.cotation = cotation;
		this.id_secteur = id_secteur;
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
	public String getCotation() {
		return cotation;
	}
	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	public Integer getId_secteur() {
		return id_secteur;
	}
	public void setId_secteur(Integer id_secteur) {
		this.id_secteur = id_secteur;
	}
	
	  
}
