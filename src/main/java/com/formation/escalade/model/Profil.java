package com.formation.escalade.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profil {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String role;
	
	public Profil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profil(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
