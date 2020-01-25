package com.formation.escalade;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;

@Controller
public class SiteController {
	
private final ISite siteRepo;
	
	public SiteController(ISite siteRepo){
		
		this.siteRepo = siteRepo;
	}
	
	@GetMapping("/creationSite")
	
	public String creationSite(Model model) {
		
		model.addAttribute("site",new Site());
		model.addAttribute("secteur",new Secteur());
		model.addAttribute("voie",new Voie());
		model.addAttribute("longueur",new Longueur());
		
		return"creation_site";
	}

}
