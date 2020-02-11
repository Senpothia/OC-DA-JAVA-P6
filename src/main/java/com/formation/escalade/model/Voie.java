package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity

public class Voie {
	
	@Id
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	private String cotation;
	
	@ManyToOne
	private Secteur secteur;
	
	@OneToMany(mappedBy="voie")
	private List<Longueur> longueurs;

	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voie(Integer id, String nom, String cotation, Secteur secteur, List<Longueur> longueurs) {
		super();
		this.id = id;
		this.nom = nom;
		this.cotation = cotation;
		this.secteur = secteur;
		this.longueurs = longueurs;
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

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public List<Longueur> getLongueurs() {
		return longueurs;
	}

	public void setLongueurs(List<Longueur> longueurs) {
		this.longueurs = longueurs;
	}

	
	
	
}
