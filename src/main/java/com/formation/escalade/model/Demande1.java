package com.formation.escalade.model;

public class Demande1 {
	
	private Integer id;
	private Utilisateur demandeur;
	private Topo topo;
	private Boolean active;
	private Boolean acceptee;
	
	public Demande1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demande1(Integer id, Utilisateur demandeur, Topo topo, Boolean active, Boolean acceptee) {
		
		super();
		this.id = id;
		this.demandeur = demandeur;
		this.topo = topo;
		this.active = active;
		this.acceptee = acceptee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Utilisateur getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(Utilisateur demandeur) {
		this.demandeur = demandeur;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getAcceptee() {
		return acceptee;
	}

	public void setAcceptee(Boolean acceptee) {
		this.acceptee = acceptee;
	}
	
}
