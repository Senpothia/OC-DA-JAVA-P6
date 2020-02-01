package com.formation.escalade;

import org.springframework.stereotype.Controller;

import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.IVoie;

@Controller
public class SecteurController {
	
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	
	public SecteurController(ISecteur secteurRepo,IVoie voieRepo, ILongueur longueurRepo) {

		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		
	}
	
	
	

}
