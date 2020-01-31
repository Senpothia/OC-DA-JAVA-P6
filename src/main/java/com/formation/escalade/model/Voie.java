package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="voie")
public class Voie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	private String cotation;
	@OneToOne
	@JoinColumn(name="id_secteur")
	private Secteur secteur;
	public Voie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Voie(Integer id, String nom, String cotation, Secteur secteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.cotation = cotation;
		this.secteur = secteur;
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
	@Override
	public String toString() {
		return "Voie [id=" + id + ", nom=" + nom + ", cotation=" + cotation + ", secteur=" + secteur + "]";
	}
	
	  
}
