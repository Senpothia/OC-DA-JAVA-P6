package com.formation.escalade.model;

import java.util.Date;

public class FormTopo {

public String nomSite;
public String nom;
public String description;
public String lieu;
public Date date;
public boolean disponibilite;

public FormTopo() {
	super();
	// TODO Auto-generated constructor stub
}

public FormTopo(String nomSite, String nom, String description, String lieu, Date date) {
	super();
	this.nomSite = nomSite;
	this.nom = nom;
	this.description = description;
	this.lieu = lieu;
	this.date = date;
}

public String getNomSite() {
	return nomSite;
}

public void setNomSite(String nomSite) {
	this.nomSite = nomSite;
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

public void setLieu(String lieu) {
	this.lieu = lieu;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}


}
