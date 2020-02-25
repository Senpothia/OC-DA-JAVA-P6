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
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Demande",
				joinColumns = @JoinColumn(name="id_utilisateur"),
				inverseJoinColumns = @JoinColumn(name="id_topo")
				)
	private List<Demande> demandes;
	
	public Utilisateur() {
		
	}
	
	
	
	public Integer getId() {
		return id;
	}public Utilisateur(Integer id, String nom, String prenom, int departement, String email, String passe,
			boolean membre, List<Commentaire> commentaires, List<Demande> demandes) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.email = email;
		this.passe = passe;
		this.membre = membre;
		this.commentaires = commentaires;
		this.demandes = demandes;
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
	
	/**
	public List<Commentaire_site> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<Commentaire_site> commentaires) {
		this.commentaires = commentaires;
	}
	
	*/
	
	public List<Demande> getDemandes() {
		return demandes;
	}
	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", departement=" + departement
				+ ", email=" + email + ", passe=" + passe + ", membre=" + membre + "]";
	}



	public List<Commentaire> getCommentaires() {
		return commentaires;
	}



	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	

}
