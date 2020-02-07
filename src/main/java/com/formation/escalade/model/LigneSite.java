package com.formation.escalade.model;

public class LigneSite {
	
	private String nomSite;
	private String nomSecteur;
	private String nomVoie;
	private String nomLongueur;
	public LigneSite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LigneSite(String nomSite, String nomSecteur, String nomVoie, String nomLongueur) {
		super();
		this.nomSite = nomSite;
		this.nomSecteur = nomSecteur;
		this.nomVoie = nomVoie;
		this.nomLongueur = nomLongueur;
	}
	public String getNomSite() {
		return nomSite;
	}
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
	public String getNomVoie() {
		return nomVoie;
	}
	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}
	public String getNomLongueur() {
		return nomLongueur;
	}
	public void setNomLongueur(String nomLongueur) {
		this.nomLongueur = nomLongueur;
	}
	@Override
	public String toString() {
		return "TableSite [nomSite=" + nomSite + ", nomSecteur=" + nomSecteur + ", nomVoie=" + nomVoie
				+ ", nomLongueur=" + nomLongueur + "]";
	}
	
	

}
