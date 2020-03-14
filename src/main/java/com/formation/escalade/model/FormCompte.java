package com.formation.escalade.model;

public class FormCompte {
	
	private String prenom;
	private String nom;
	private Integer departement;
	private String email;
	private String password;
	
	public FormCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormCompte(String prenom, String nom, Integer departement, String email, String password) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.departement = departement;
		this.email = email;
		this.password = password;
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

	@Override
	public String toString() {
		return "FormCompte [prenom=" + prenom + ", nom=" + nom + ", departement=" + departement + ", email=" + email
				+ ", password=" + password + "]";
	}
	
}
