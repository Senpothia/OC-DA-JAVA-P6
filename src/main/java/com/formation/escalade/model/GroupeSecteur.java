package com.formation.escalade.model;

import java.util.List;

public class GroupeSecteur {
	
	private String nomSite;
	private List<String> voies;
	private List<String> longueurs;
	
	public GroupeSecteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupeSecteur(String nom, List<String> voies, List<String> longueurs) {
		super();
		this.nomSite = nom;
		this.voies = voies;
		this.longueurs = longueurs;
	}

	public String getNom() {
		return nomSite;
	}

	public void setNom(String nom) {
		this.nomSite = nom;
	}

	public List<String> getVoies() {
		return voies;
	}

	public void setVoies(List<String> voies) {
		this.voies = voies;
	}

	public List<String> getLongueurs() {
		return longueurs;
	}

	public void setLongueurs(List<String> longueurs) {
		this.longueurs = longueurs;
	}

	@Override
	public String toString() {
		return "LigneSecteur [nom=" + nomSite + ", voies=" + voies + ", longueurs=" + longueurs + "]";
	}
	
	
	

}
