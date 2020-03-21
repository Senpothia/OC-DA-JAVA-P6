package com.formation.escalade.model;

public class FormUser {
	
	private String nom;
	private String prenom;
	private String email;
	
	public FormUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormUser(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
