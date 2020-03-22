package com.formation.escalade.model;

public class FormCompte {
	
	private String prenom;
	private String nom;
	private Integer departement;
	private String email;
	private String password;
	private Boolean membre;
	
	public FormCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormCompte(String prenom, String nom, Integer departement, String email, String password, Boolean membre) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.departement = departement;
		this.email = email;
		this.password = password;
		this.membre = membre;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getDepartement() {
		return departement;
	}

	public void setDepartement(Integer departement) {
		this.departement = departement;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getMembre() {
		return membre;
	}

	public void setMembre(Boolean membre) {
		this.membre = membre;
	}

	
	
}
