package com.formation.escalade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;

@Controller
public class SiteController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	
	
	public SiteController(ISite siteRepo, ISecteur secteurRepo,IVoie voieRepo, ILongueur longueurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
	}

	@GetMapping("/creationSite")

	public String creationSite(Model model) {

		model.addAttribute("formSite", new FormSite());

		return "creation_site";
	}

	@PostMapping("/creationSite")

	public String siteSubmit(FormSite formSite) {

		System.out.println(formSite.toString());

		String nomSite = formSite.getNomSite();
		String localisationSite = formSite.getLocalisationSite();
		String departementSite = formSite.getDepartementSite();
		boolean officielSite = false;

		String nomSecteur = formSite.getNomSecteur();
		
		String remSecteur = formSite.getRemSite();  // A corriger: A mettre dans la classe commentaire_site

		String nomVoie = formSite.getNomVoie();
		
		String cotationVoie = formSite.getCotationVoie();

		String nomLongueur = formSite.getNomLongueur();
		
		int nbreSpit = formSite.getNbreSpit();
		String cotationLongueur = formSite.getCotationLongueur();
		String remLongueur = formSite.getRemLongueur();

		Site site = new Site();
		site.setNom(nomSite);
		site.setLocalisation(localisationSite);
		site.setDepartement(departementSite);
		site.setOfficiel(officielSite);
		
		siteRepo.save(site);
		
		Secteur secteur = new Secteur();
		secteur.setNom(nomSecteur);
	

		secteurRepo.save(secteur);
		//voieRepo.save(voie);
		//longueurRepo.save(longueur);

		return "creation_site";
	}

}
