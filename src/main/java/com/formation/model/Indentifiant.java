package com.formation.model;

import org.springframework.stereotype.Component;

@Component
public class Indentifiant {
	
	public String email;
	public String passe;
	public Indentifiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Indentifiant(String email, String passe) {
		super();
		this.email = email;
		this.passe = passe;
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
	
	

}
