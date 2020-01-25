package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Commentaire_longueur {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String texte;
	private boolean actif;
	private Integer id_site;
	private Integer id_utilisateur;
	public Commentaire_longueur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Commentaire_longueur(Integer id, String texte, boolean actif, Integer id_site, Integer id_utilisateur) {
		super();
		this.id = id;
		this.texte = texte;
		this.actif = actif;
		this.id_site = id_site;
		this.id_utilisateur = id_utilisateur;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public Integer getId_site() {
		return id_site;
	}
	public void setId_site(Integer id_site) {
		this.id_site = id_site;
	}
	public Integer getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(Integer id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	
	

}
