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
		
		model.addAttribute("formSite",new FormSite());
		
		
		return"creation_site";
	}
	
	@PostMapping("/creationSite")
	
	public String siteSubmit(@ModelAttribute FormSite formSite, Model model) {
		
		
		model.addAttribute("formSite", formSite);
		System.out.println(formSite.toString());
		
		
		return"creation_site";
	}

}
