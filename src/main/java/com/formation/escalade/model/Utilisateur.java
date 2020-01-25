package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Utilisateur {

	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String prenom;
	private int departement;
	private String email;
	private String passe;
	private boolean membre;
	public Utilisateur() {
		
	}
	public Utilisateur(Integer id, String nom, String prenom, int departement, String email, String passe,
			boolean membre) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.email = email;
		this.passe = passe;
		this.membre = membre;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getDepartement() {
		return departement;
	}
	public void setDepartement(int departement) {
		this.departement = departement;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasse() {
		return passe;
	}
	public void setPasse(String passe) {
		this.passe = passe;
	}
	public boolean isMembre() {
		return membre;
	}
	public void setMembre(boolean membre) {
		this.membre = membre;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", departement=" + departement
				+ ", email=" + email + ", passe=" + passe + ", membre=" + membre + "]";
	}
	
	

}
