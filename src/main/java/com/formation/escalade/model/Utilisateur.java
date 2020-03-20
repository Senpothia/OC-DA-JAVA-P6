package com.formation.escalade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String prenom;
	private int departement;
	private String email;
	private String passe;
	private boolean membre;
	
	@OneToMany(mappedBy="auteur")
	private List<Commentaire> commentaires;
	

	@OneToMany(mappedBy="proprietaire")
	private List<Topo> topos;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Demande",
				joinColumns=@JoinColumn(name = "demandeur"), 
				inverseJoinColumns = @JoinColumn(name = "topo")
			)
	private List<Topo> emprunts;
	/*
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="UserProfil",
				joinColumns=@JoinColumn(name = "idUser"), 
				inverseJoinColumns = @JoinColumn(name ="idProfil")
			)
	private List<Profil> profils; 
*/
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Utilisateur(Integer id, String nom, String prenom, int departement, String email, String passe,
			boolean membre, List<Commentaire> commentaires, List<Topo> topos, List<Topo> emprunts) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.email = email;
		this.passe = passe;
		this.membre = membre;
		this.commentaires = commentaires;
		this.topos = topos;
		this.emprunts = emprunts;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getDepartement() {
		return departement;
	}
	public void setDepartement(int departement) {
		this.departement = departement;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasse() {
		return passe;
	}
	public void setPasse(String passe) {
		this.passe = passe;
	}
	public boolean isMembre() {
		return membre;
	}
	public void setMembre(boolean membre) {
		this.membre = membre;
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
	public List<Topo> getEmprunts() {
		return emprunts;
	}
	public void setEmprunts(List<Topo> emprunts) {
		this.emprunts = emprunts;
	}

	
	
	
}
