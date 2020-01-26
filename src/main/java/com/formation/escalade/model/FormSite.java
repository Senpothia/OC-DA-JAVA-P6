package com.formation.escalade.model;

public class FormSite {

	private String nomSite;
	private String lieuSite;
	private String departementSite;
	private String nomSecteur;
	private int numSecteur;
	private String remSecteur;
	private int numVoie;
	private String cotationVoie;
	private int numLongueur;
	private String cotationLongueur;

	public FormSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormSite(String nomSite, String lieuSite, String departementSite, String nomSecteur, int numSecteur,
			String remSecteur, int numVoie, String cotationVoie, int numLongueur, String cotationLongueur) {
		super();
		this.nomSite = nomSite;
		this.lieuSite = lieuSite;
		this.departementSite = departementSite;
		this.nomSecteur = nomSecteur;
		this.numSecteur = numSecteur;
		this.remSecteur = remSecteur;
		this.numVoie = numVoie;
		this.cotationVoie = cotationVoie;
		this.numLongueur = numLongueur;
		this.cotationLongueur = cotationLongueur;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getLieuSite() {
		return lieuSite;
	}

	public void setLieuSite(String lieuSite) {
		this.lieuSite = lieuSite;
	}

	public String getDepartementSite() {
		return departementSite;
	}

	public void setDepartementSite(String departementSite) {
		this.departementSite = departementSite;
	}

	public String getNomSecteur() {
		return nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}

	public int getNumSecteur() {
		return numSecteur;
	}

	public void setNumSecteur(int numSecteur) {
		this.numSecteur = numSecteur;
	}

	public String getRemSecteur() {
		return remSecteur;
	}

	public void setRemSecteur(String remSecteur) {
		this.remSecteur = remSecteur;
	}

	public int getNumVoie() {
		return numVoie;
	}

	public void setNumVoie(int numVoie) {
		this.numVoie = numVoie;
	}

	public String getCotationVoie() {
		return cotationVoie;
	}

	public void setCotationVoie(String cotationVoie) {
		this.cotationVoie = cotationVoie;
	}

	public int getNumLongueur() {
		return numLongueur;
	}

	public void setNumLongueur(int numLongueur) {
		this.numLongueur = numLongueur;
	}

	public String getCotationLongueur() {
		return cotationLongueur;
	}

	public void setCotationLongueur(String cotationLongueur) {
		this.cotationLongueur = cotationLongueur;
	}

	@Override
	public String toString() {
		return "FormSite [nomSite=" + nomSite + ", lieuSite=" + lieuSite + ", departementSite=" + departementSite
				+ ", nomSecteur=" + nomSecteur + ", numSecteur=" + numSecteur + ", remSecteur=" + remSecteur
				+ ", numVoie=" + numVoie + ", cotationVoie=" + cotationVoie + ", numLongueur=" + numLongueur
				+ ", cotationLongueur=" + cotationLongueur + "]";
	}

}
