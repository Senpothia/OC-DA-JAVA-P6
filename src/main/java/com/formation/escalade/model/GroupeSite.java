package com.formation.escalade.model;

import java.util.List;

public class GroupeSite {
	
	private String nomSite;
	private List<String> secteurs;
	private List<List<String>> voies;
	private List<List<String>> longueurs;
	
	public GroupeSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupeSite(String nomSite, List<String> secteurs, List<List<String>> voies, List<List<String>> longueurs) {
		super();
		this.nomSite = nomSite;
		this.secteurs = secteurs;
		this.voies = voies;
		this.longueurs = longueurs;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public List<String> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<String> secteurs) {
		this.secteurs = secteurs;
	}

	public List<List<String>> getVoies() {
		return voies;
	}

	public void setVoies(List<List<String>> voies) {
		this.voies = voies;
	}

	public List<List<String>> getLongueurs() {
		return longueurs;
	}

	public void setLongueurs(List<List<String>> longueurs) {
		this.longueurs = longueurs;
	}

	@Override
	public String toString() {
		return "GroupeSite [nomSite=" + nomSite + ", secteurs=" + secteurs + ", voies=" + voies + ", longueurs="
				+ longueurs + "]";
	}
	
	
	

}
