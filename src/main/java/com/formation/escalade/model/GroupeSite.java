package com.formation.escalade.model;

import java.util.List;

public class GroupeSite {
	
	private String nomSite;
	private String secteurs;
	private List<String> voies;
	private List<String> longueurs;
	public GroupeSite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupeSite(String nomSite, String secteurs, List<String> voies, List<String> longueurs) {
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
	public String getSecteurs() {
		return secteurs;
	}
	public void setSecteurs(String secteurs) {
		this.secteurs = secteurs;
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
		return "GroupeSite [nomSite=" + nomSite + ", secteurs=" + secteurs + ", voies=" + voies + ", longueurs="
				+ longueurs + "]";
	}
	
	
	

}
