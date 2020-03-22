package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nom"})})
public class Site {

	@Id
	@GeneratedValue
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(unique=true)
	private String nom;
	private String localisation;
	private int departement;
	private boolean officiel;
	@OneToMany(mappedBy="site")
	private List<Secteur> secteurs; 
	@OneToMany(mappedBy="site")
	private List<Commentaire> commentaires;
	@OneToMany(mappedBy="site")
	private List<Topo> topos;
	private Integer createur;  // id du créateur du site
	
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Site(Integer id, String nom, String localisation, int departement, boolean officiel, List<Secteur> secteurs,
			List<Commentaire> commentaires, List<Topo> topos, Integer createur) {
		super();
		this.id = id;
		this.nom = nom;
		this.localisation = localisation;
		this.departement = departement;
		this.officiel = officiel;
		this.secteurs = secteurs;
		this.commentaires = commentaires;
		this.topos = topos;
		this.createur = createur;
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

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Topo> getTopos() {
		return topos;
	}

	public void setTopos(List<Topo> topos) {
		this.topos = topos;
	}

	public Integer getCreateur() {
		return createur;
	}

	public void setCreateur(Integer createur) {
		this.createur = createur;
	}
	
	

}
