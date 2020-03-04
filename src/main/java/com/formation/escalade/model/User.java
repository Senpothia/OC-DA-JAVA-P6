package com.formation.escalade.model;

public class User {

	private String nom;
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String nom, String password) {
		super();
		this.nom = nom;
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [nom=" + nom + ", password=" + password + "]";
	}
	
	
	
	
}
