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
	
	@OneToOne
	@JoinColumn(name="id_site")
	private Site site;
	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Secteur(Integer id, String nom, Integer numSecteur, Site site) {
		super();
		this.id = id;
		this.nom = nom;
		
		this.site = site;
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
	
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "Secteur [id=" + id + "site=" + site + "]";
	}
	
	
	
	
	
}
