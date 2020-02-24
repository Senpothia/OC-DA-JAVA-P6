package com.formation.escalade.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Topo {

	@Id
	@GeneratedValue
	private Integer id;
	private boolean disponible;
	private Utilisateur proprietaire;
	private List<Utilisateur> demandeurs;
	private Utilisateur emprunteur;
	private Site site;
	
	public Topo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topo(Integer id, boolean disponible, Utilisateur proprietaire, List<Utilisateur> demandeurs,
			Utilisateur emprunteur, Site site) {
		super();
		this.id = id;
		this.disponible = disponible;
		this.proprietaire = proprietaire;
		this.demandeurs = demandeurs;
		this.emprunteur = emprunteur;
		this.site = site;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Utilisateur> getDemandeurs() {
		return demandeurs;
	}

	public void setDemandeurs(List<Utilisateur> demandeurs) {
		this.demandeurs = demandeurs;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
}
