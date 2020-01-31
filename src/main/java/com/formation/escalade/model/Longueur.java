package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="longueur")
public class Longueur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer spit;
	private String nom;
	private String cotation;
	@OneToOne
	@JoinColumn(name="id_voie")
	private Integer id_voie;
	public Longueur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Longueur(Integer id, Integer spit, String nom, String cotation, Integer id_voie) {
		super();
		this.id = id;
		this.spit = spit;
		this.nom = nom;
		this.cotation = cotation;
		this.id_voie = id_voie;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpit() {
		return spit;
	}
	public void setSpit(Integer spit) {
		this.spit = spit;
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
	public Integer getId_voie() {
		return id_voie;
	}
	public void setId_voie(Integer id_voie) {
		this.id_voie = id_voie;
	}
	
	
}
