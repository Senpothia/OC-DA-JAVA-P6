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
		int numSecteur = formSite.getNumSecteur();
		String remSecteur = formSite.getRemSecteur();

		String nomVoie = formSite.getNomVoie();
		int numVoie = formSite.getNumVoie();
		String cotationVoie = formSite.getCotationVoie();

		String nomLongueur = formSite.getNomLongueur();
		int numLongueur = formSite.getNumLongueur();
		int nbreSpit = formSite.getNbreSpit();
		String cotationLongueur = formSite.getCotationLongueur();

		Site site = new Site(nomSite, localisationSite, departementSite, officielSite);
		Secteur secteur = new Secteur(nomSecteur,  numSecteur, remSecteur);
		Voie voie = new Voie(nomVoie, numVoie, cotationVoie);
		Longueur longueur = new Longueur(nomLongueur, numLongueur,  nbreSpit );
		
		siteRepo.save(site);
		secteurRepo.save(secteur);
		voieRepo.save(voie);
		longueurRepo.save(longueur);

		return "creation_site";
	}

}
