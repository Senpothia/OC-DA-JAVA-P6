package com.formation.escalade.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Topo {

	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String description;
	private String lieu;
	private Date date;
	private boolean disponible;
	private Integer idUtilisateur;
	private Integer idSite;
	public Topo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Topo(Integer id, String nom, String description, String lieu, Date date, boolean disponible,
			Integer id_utilisateur, Integer idSite) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.lieu = lieu;
		this.date = date;
		this.disponible = disponible;
		this.idUtilisateur = id_utilisateur;
		this.idSite = idSite;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieux) {
		this.lieu = lieux;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Integer getId_utilisateur() {
		return idUtilisateur;
	}
	public void setId_utilisateur(Integer id_utilisateur) {
		this.idUtilisateur = id_utilisateur;
	}
	public Integer getId_site() {
		return idSite;
	}
	public void setId_site(Integer id_site) {
		this.idSite = id_site;
	}
	@Override
	public String toString() {
		return "Topo [id=" + id + ", nom=" + nom + ", description=" + description + ", lieu=" + lieu + ", date=" + date
				+ ", disponible=" + disponible + ", idUtilisateur=" + idUtilisateur + ", idSite=" + idSite + "]";
	}
	
	
}
