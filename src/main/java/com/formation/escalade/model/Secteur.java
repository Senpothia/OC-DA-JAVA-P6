package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nom"})})
public class Secteur {
	
	@Id
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	
	@ManyToOne
	//@JoinColumn(name="id_site")
	private Site site;
	
	@OneToMany(mappedBy="secteur")
	private List<Voie> voies;

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Secteur(Integer id, String nom, Site site, List<Voie> voies) {
		super();
		this.id = id;
		this.nom = nom;
		this.site = site;
		this.voies = voies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Voie> getVoies() {
		return voies;
	}

	public void setVoies(List<Voie> voies) {
		this.voies = voies;
	}

	
	
	
}
