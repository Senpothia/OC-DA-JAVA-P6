package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name= "secteur")
public class Secteur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	private Integer numSecteur;
	@OneToOne
	@JoinColumn(name="id_site")
	private Site site;
	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Secteur(Integer id, String nom, Integer numSecteur, Integer id_site) {
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
	public Integer getNumSecteur() {
		return numSecteur;
	}
	public void setNumSecteur(Integer numSecteur) {
		this.numSecteur = numSecteur;
	}
	public Integer getId_site() {
		return id_site;
	}
	public void setId_site(Integer id_site) {
		this.id_site = id_site;
	}
	@Override
	public String toString() {
		return "Secteur [id=" + id + ", nom=" + nom + ", numSecteur=" + numSecteur + ", id_site=" + id_site + "]";
	}
	
	
	
	
	
	
	
}
