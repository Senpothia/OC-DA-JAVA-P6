package com.formation.escalade.model;

public class FormSite {

	private String nomSite;
	private String localisationSite;
	private String departementSite;
	
	private String nomSecteur;
	private int numSecteur;
	private String remSecteur;
	
	private int numVoie;
	private String nomVoie;
	private String cotationVoie;
	
	
	private int numLongueur;
	private int nbreSpit;
	private String nomLongueur;
	private String cotationLongueur;
	
	public FormSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormSite(String nomSite, String localisationSite, String departementSite, String nomSecteur, int numSecteur,
			String remSecteur, int numVoie, String nomVoie, String cotationVoie, int numLongueur, int nbreSpit,
			String nomLongueur, String cotationLongueur) {
		super();
		this.nomSite = nomSite;
		this.localisationSite = localisationSite;
		this.departementSite = departementSite;
		this.nomSecteur = nomSecteur;
		this.numSecteur = numSecteur;
		this.remSecteur = remSecteur;
		this.numVoie = numVoie;
		this.nomVoie = nomVoie;
		this.cotationVoie = cotationVoie;
		this.numLongueur = numLongueur;
		this.nbreSpit = nbreSpit;
		this.nomLongueur = nomLongueur;
		this.cotationLongueur = cotationLongueur;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getLocalisationSite() {
		return localisationSite;
	}

	public void setLocalisationSite(String localisationSite) {
		this.localisationSite = localisationSite;
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

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
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

	public int getNbreSpit() {
		return nbreSpit;
	}

	public void setNbreSpit(int nbreSpit) {
		this.nbreSpit = nbreSpit;
	}

	public String getNomLongueur() {
		return nomLongueur;
	}

	public void setNomLongueur(String nomLongueur) {
		this.nomLongueur = nomLongueur;
	}

	public String getCotationLongueur() {
		return cotationLongueur;
	}

	public void setCotationLongueur(String cotationLongueur) {
		this.cotationLongueur = cotationLongueur;
	}

	@Override
	public String toString() {
		return "FormSite [nomSite=" + nomSite + ", localisationSite=" + localisationSite + ", departementSite="
				+ departementSite + ", nomSecteur=" + nomSecteur + ", numSecteur=" + numSecteur + ", remSecteur="
				+ remSecteur + ", numVoie=" + numVoie + ", nomVoie=" + nomVoie + ", cotationVoie=" + cotationVoie
				+ ", numLongueur=" + numLongueur + ", nbreSpit=" + nbreSpit + ", nomLongueur=" + nomLongueur
				+ ", cotationLongueur=" + cotationLongueur + "]";
	}

	
}
