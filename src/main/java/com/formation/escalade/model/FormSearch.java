package com.formation.escalade.model;

public class FormSearch {
	
	private String nom;
	private String type;
	private String createur;
	private int departement;
	private String localisation;
	private int secteurs;
	private String secteurs_crit;
	private int voies;
	private String voies_crit;
	private int longueurs;
	private String longueurs_crit;
	private int spits;
	private String spits_crit;
	private int topos;
	private String topos_crit;
	private int topos_dispo;
	private String topos_dispo_crit;
	private String cotation;
	private String cotation_crit;
	
	public FormSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormSearch(String nom, String type, String createur, int departement, String localisation, int secteurs,
			String secteurs_crit, int voies, String voies_crit, int longueurs, String longueurs_crit, int spits,
			String spits_crit, int topos, String topos_crit, int topos_dispo, String topos_dispo_crit, String cotation,
			String cotation_crit) {
		super();
		this.nom = nom;
		this.type = type;
		this.createur = createur;
		this.departement = departement;
		this.localisation = localisation;
		this.secteurs = secteurs;
		this.secteurs_crit = secteurs_crit;
		this.voies = voies;
		this.voies_crit = voies_crit;
		this.longueurs = longueurs;
		this.longueurs_crit = longueurs_crit;
		this.spits = spits;
		this.spits_crit = spits_crit;
		this.topos = topos;
		this.topos_crit = topos_crit;
		this.topos_dispo = topos_dispo;
		this.topos_dispo_crit = topos_dispo_crit;
		this.cotation = cotation;
		this.cotation_crit = cotation_crit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public int getDepartement() {
		return departement;
	}

	public void setDepartement(int departement) {
		this.departement = departement;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public int getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(int secteurs) {
		this.secteurs = secteurs;
	}

	public String getSecteurs_crit() {
		return secteurs_crit;
	}

	public void setSecteurs_crit(String secteurs_crit) {
		this.secteurs_crit = secteurs_crit;
	}

	public int getVoies() {
		return voies;
	}

	public void setVoies(int voies) {
		this.voies = voies;
	}

	public String getVoies_crit() {
		return voies_crit;
	}

	public void setVoies_crit(String voies_crit) {
		this.voies_crit = voies_crit;
	}

	public int getLongueurs() {
		return longueurs;
	}

	public void setLongueurs(int longueurs) {
		this.longueurs = longueurs;
	}

	public String getLongueurs_crit() {
		return longueurs_crit;
	}

	public void setLongueurs_crit(String longueurs_crit) {
		this.longueurs_crit = longueurs_crit;
	}

	public int getSpits() {
		return spits;
	}

	public void setSpits(int spits) {
		this.spits = spits;
	}

	public String getSpits_crit() {
		return spits_crit;
	}

	public void setSpits_crit(String spits_crit) {
		this.spits_crit = spits_crit;
	}

	public int getTopos() {
		return topos;
	}

	public void setTopos(int topos) {
		this.topos = topos;
	}

	public String getTopos_crit() {
		return topos_crit;
	}

	public void setTopos_crit(String topos_crit) {
		this.topos_crit = topos_crit;
	}

	public int getTopos_dispo() {
		return topos_dispo;
	}

	public void setTopos_dispo(int topos_dispo) {
		this.topos_dispo = topos_dispo;
	}

	public String getTopos_dispo_crit() {
		return topos_dispo_crit;
	}

	public void setTopos_dispo_crit(String topos_dispo_crit) {
		this.topos_dispo_crit = topos_dispo_crit;
	}

	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public String getCotation_crit() {
		return cotation_crit;
	}

	public void setCotation_crit(String cotation_crit) {
		this.cotation_crit = cotation_crit;
	}

	@Override
	public String toString() {
		return "FormSearch [nom=" + nom + ", type=" + type + ", createur=" + createur + ", departement=" + departement
				+ ", localisation=" + localisation + ", secteurs=" + secteurs + ", secteurs_crit=" + secteurs_crit
				+ ", voies=" + voies + ", voies_crit=" + voies_crit + ", longueurs=" + longueurs + ", longueurs_crit="
				+ longueurs_crit + ", spits=" + spits + ", spits_crit=" + spits_crit + ", topos=" + topos
				+ ", topos_crit=" + topos_crit + ", topos_dispo=" + topos_dispo + ", topos_dispo_crit="
				+ topos_dispo_crit + ", cotation=" + cotation + ", cotation_crit=" + cotation_crit + "]";
	}

	
	
}
