package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Site {

	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String localisation;
	private String departement;
	private boolean officiel;
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Site(Integer id, String nom, String localisation, String departement, boolean officiel) {
		super();
		this.id = id;
		this.nom = nom;
		this.localisation = localisation;
		this.departement = departement;
		this.officiel = officiel;
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
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public boolean isOfficiel() {
		return officiel;
	}
	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
	}
	
	

}
