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
	private Voie voie;
	public Longueur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Longueur(Integer id, Integer spit, String nom, String cotation, Voie voie) {
		super();
		this.id = id;
		this.spit = spit;
		this.nom = nom;
		this.cotation = cotation;
		this.voie = voie;
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
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
	}
	@Override
	public String toString() {
		return "Longueur [id=" + id + ", spit=" + spit + ", nom=" + nom + ", cotation=" + cotation + ", voie=" + voie
				+ "]";
	}
	
	
	
	
}
