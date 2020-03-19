package com.formation.escalade.model;

public class GroupeGal {
	
	private String nomSite;
	private Boolean officiel;
	
	public GroupeGal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupeGal(String nomSite, Boolean officiel) {
		super();
		this.nomSite = nomSite;
		this.officiel = officiel;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public Boolean getOfficiel() {
		return officiel;
	}

	public void setOfficiel(Boolean officiel) {
		this.officiel = officiel;
	}

	@Override
	public String toString() {
		return "GroupeGal [nomSite=" + nomSite + ", officiel=" + officiel + "]";
	}
	
	
	

}
