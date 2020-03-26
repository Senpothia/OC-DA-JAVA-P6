package com.formation.escalade.model;

public class FormSearch {
	
	private String nom;
	private String type;
	private String createur;
	private int departement;
	private String localisation;
	private int secteur;
	private String secteur_crit;
	private int voie;
	private String voie_crit;
	private int longueur;
	private String longueur_crit;
	private int spits;
	private String spitsr_crit;
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

	public FormSearch(String nom, String type, String createur, int departement, String localisation, int secteur,
			String secteur_crit, int voie, String voie_crit, int longueur, String longueur_crit, int spits,
			String spitsr_crit, int topos, String topos_crit, int topos_dispo, String topos_dispo_crit, String cotation,
			String cotation_crit) {
		super();
		this.nom = nom;
		this.type = type;
		this.createur = createur;
		this.departement = departement;
		this.localisation = localisation;
		this.secteur = secteur;
		this.secteur_crit = secteur_crit;
		this.voie = voie;
		this.voie_crit = voie_crit;
		this.longueur = longueur;
		this.longueur_crit = longueur_crit;
		this.spits = spits;
		this.spitsr_crit = spitsr_crit;
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

	public int getSecteur() {
		return secteur;
	}

	public void setSecteur(int secteur) {
		this.secteur = secteur;
	}

	public String getSecteur_crit() {
		return secteur_crit;
	}

	public void setSecteur_crit(String secteur_crit) {
		this.secteur_crit = secteur_crit;
	}

	public int getVoie() {
		return voie;
	}

	public void setVoie(int voie) {
		this.voie = voie;
	}

	public String getVoie_crit() {
		return voie_crit;
	}

	public void setVoie_crit(String voie_crit) {
		this.voie_crit = voie_crit;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public String getLongueur_crit() {
		return longueur_crit;
	}

	public void setLongueur_crit(String longueur_crit) {
		this.longueur_crit = longueur_crit;
	}

	public int getSpits() {
		return spits;
	}

	public void setSpits(int spits) {
		this.spits = spits;
	}

	public String getSpitsr_crit() {
		return spitsr_crit;
	}

	public void setSpitsr_crit(String spitsr_crit) {
		this.spitsr_crit = spitsr_crit;
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

}
