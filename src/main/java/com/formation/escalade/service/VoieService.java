package com.formation.escalade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IVoie;

@Service
public class VoieService {

	@Autowired
	SecteurService secteurService;
	@Autowired
	private final ISite siteRepo;
	@Autowired
	private final ISecteur secteurRepo;
	@Autowired
	private final IVoie voieRepo;
	@Autowired
	private final ILongueur longueurRepo;
	@Autowired
	private final CommentaireRepo commentaireRepo;
	
	public VoieService(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {
		super();
		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

}
