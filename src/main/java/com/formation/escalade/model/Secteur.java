package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Secteur {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private Integer id_site;
	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Secteur(Integer id, String nom, Integer id_site) {
		super();
		this.id = id;
		this.nom = nom;
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
	public Integer getId_site() {
		return id_site;
	}
	public void setId_site(Integer id_site) {
		this.id_site = id_site;
	}
	
	
}
