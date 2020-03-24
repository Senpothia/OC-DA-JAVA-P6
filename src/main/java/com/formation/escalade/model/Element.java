package com.formation.escalade.model;

public class Element<T> {
	
	private T branche;   // Se rapporte à un élément de site, une branche de la structure de site

	public Element() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Element(T branche) {
		super();
		this.branche = branche;
	}

	public T getBranche() {
		return branche;
	}

	public void setBranche(T branche) {
		this.branche = branche;
	}

	public Boolean isPresent() {
		
		if (this.branche == null) {
			
			return false;
		} else {
			
			return true;
			}
	}
}
