package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="site")
public class Site {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nom;
	private String localisation;
	private int departement;
	private boolean officiel;
	
	@OneToMany(mappedBy="site")
	private List<Commentaire> commentaires;
	
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Site(Integer id, String nom, String localisation, int departement, boolean officiel) {
		super();
		this.id = id;
		this.nom = nom;
		this.localisation = localisation;
		this.departement = departement;
		this.officiel = officiel;
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
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public int getDepartement() {
		return departement;
	}
	public void setDepartement(int departement) {
		this.departement = departement;
	}
	public boolean isOfficiel() {
		return officiel;
	}
	public void setOfficiel(boolean officiel) {
		this.officiel = officiel;
	}


	@Override
	public String toString() {
		return "Site [id=" + id + ", nom=" + nom + ", localisation=" + localisation + ", departement=" + departement
				+ ", officiel=" + officiel + "]";
	}
	
	

}
