package com.formation.escalade;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.LongueurService;
import com.formation.escalade.service.SiteService;
import com.formation.escalade.service.VoieService;

@Controller
public class LongueurController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;

	@Autowired
	LongueurService longueurService;

	public LongueurController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
	}

	@GetMapping("/site/{id}/longueurs")
	public String ajouterLongueur(@PathVariable("id") Integer siteId, Model model
			, HttpSession session) {

		session.setAttribute("IDSITE", siteId);
		Site site = siteRepo.getOne(siteId);
		model.addAttribute("site", site);
		String nomSecteur = new String();
		model.addAttribute("nomSecteur", nomSecteur);
		return "choisirsecteur_add_long";

	}

	@PostMapping("/choisirvoie")
	public String choisirVoie(String nomSecteur, Model model, HttpSession session,
			HttpServletRequest request) {
		
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		Site site = siteRepo.getOne(siteId);
		model.addAttribute("site", site);
		System.out.println("Choix secteur:" + nomSecteur);
		session.setAttribute("NOMSECTEUR", nomSecteur);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		String nomVoie = new String();
		model.addAttribute("nomVoie", nomVoie);

		return "choisirvoie";

	}

	@PostMapping("/ajouter/longueur/secteur/site/{id}")  // Récupération nom du secteur pour ajout longueur
	public String choixSecteur_longueur(@PathVariable("id") Integer id, String nomSecteur
			, Model model
			, HttpServletRequest request, HttpSession session) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		model.addAttribute("nomVoie", new String());
		
		
		return "choisirvoie_add_long";
	}
	
	
	@PostMapping("/ajouter/longueur/voie/site/{id}")   // Transmission formulaire de création longueur
	public String choixVoie_longueur(@PathVariable("id")Integer id, String nomVoie, Model model
			, HttpServletRequest request, HttpSession session) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		
		Voie voie = voieRepo.findByNom(nomVoie);
		model.addAttribute("voie", voie );
		
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		Secteur secteur = voie.getSecteur();
		String nomSecteur = secteur.getNom();
		model.addAttribute("secteur", secteur);
		model.addAttribute("formSite",  new FormSite());
		session.setAttribute("IDSITE", id);
		session.setAttribute("NOMVOIE", nomVoie);
		session.setAttribute("NOMSECTEUR", nomSecteur);
		return "creation_longueur";
		
	}
	
	
	@PostMapping("/creationlongueur")  // Reception du formulaire creation longueur
	
	public String creationLongueur(FormSite formSite, HttpServletRequest request, Model model) {
		

		Boolean creation = longueurService.createLongueur(formSite, request);
		
		if (creation) {
			
			return "redirect:espace";
			
		}else {
			
			Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
			Site site = siteRepo.getOne(siteId);
			String nomSecteur = (String) request.getSession().getAttribute("NOMSECTEUR");
			String nomVoie = (String) request.getSession().getAttribute("NOMVOIE");
			Voie voie = voieRepo.findByNom(nomVoie);
			Secteur secteur = secteurRepo.findByNom(nomSecteur);
			model.addAttribute("secteur", secteur);
			model.addAttribute("voie", voie);
			model.addAttribute("site", site);
			model.addAttribute("formSite", new FormSite());
			model.addAttribute("erreur", true);
			return "creation_longueur";
		}
	
	}
	////////////////////////////
	
	@GetMapping("/modifier/longueur/site/{id}")
	public String modifierLongueur(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
			Principal principal, Model model) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		String nomSecteur = new String();
		model.addAttribute("nomSecteur", nomSecteur);
		model.addAttribute("phrase", new String());
		return "choisirsecteur_long";
	}

	@PostMapping("/modifier/longueur/site/{id}")
	public String modifierLongSecteur(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
			Principal principal, Model model, String nomSecteur) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		System.out.println("nom secteur récupéré: " + nomSecteur);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		String nomVoie = new String();
		model.addAttribute("nomVoie", nomVoie);
		model.addAttribute("phrase", new String());
		return "choisirvoie_long";

	}

	@PostMapping("/modifier/longueur/voie/site/{id}")
	public String modifierLongSecteurVoie(@PathVariable("id") Integer id, HttpSession session,
			HttpServletRequest request, Principal principal, Model model, String nomVoie) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		System.out.println("nom voie récupéré: " + nomVoie);

		Voie voie = voieRepo.findByNom(nomVoie);
		model.addAttribute("voie", voie);
		String nomLongueur = new String();
		model.addAttribute("nomLongueur", nomLongueur);
		model.addAttribute("phrase", new String());
		return "choisirlong_long";
	}

	@PostMapping("/modifier/longueur/longueur/site/{id}")
	public String modifierLongSecteurVoieFinalisation(@PathVariable("id") Integer id, HttpSession session,
			HttpServletRequest request, Principal principal, Model model, String nomLongueur) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		System.out.println("nom longueur récupéré: " + nomLongueur);

		Longueur longueur = longueurRepo.findByNom(nomLongueur);
		FormSite formSite = new FormSite();
		formSite.setNomLongueur(longueur.getNom());
		formSite.setNbreSpit(longueur.getSpit());
		formSite.setCotationLongueur(longueur.getCotation());
		model.addAttribute("formSite", formSite);
		model.addAttribute("phrase", new String());
		return "modifier_infos_long";
	}

	@PostMapping("/modification/informations/longueur/{nomLongueur}")
	public String modifierInfosLongueur(@PathVariable("nomLongueur") String nomLongueur, HttpSession session,
			HttpServletRequest request, Principal principal, FormSite formSite, Model model) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		System.out.println("nom longueur récupéré pour modif finale: " + nomLongueur);

		Longueur longueur = longueurRepo.findByNom(nomLongueur);
		longueur.setNom(formSite.getNomLongueur());
		longueur.setSpit(formSite.getNbreSpit());
		longueur.setCotation(formSite.getCotationLongueur());
		longueurRepo.save(longueur);
		model.addAttribute("phrase", new String());
		return "espace";
	}
	
	
	@PostMapping("/supprimer/site/longueur{id}")
	public String supprimerlongueur_secteur(@PathVariable("id") Integer id
			,@RequestParam(name = "secteur", required=false) Boolean del_secteur
			,@RequestParam(name="voie", required=false) Boolean del_voie
			,@RequestParam(name="longueur", required=false) Boolean del_longueur
			, Model model
			, HttpSession session, Principal principal
			, HttpServletRequest request,
			String nomSecteur) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("Nom secteur reçu -/-: " + nomSecteur);
		Site site = siteRepo.getOne(id);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("site", site);
		model.addAttribute("del_secteur", del_secteur);
		model.addAttribute("del_voie", del_voie);
		model.addAttribute("del_longueur", del_longueur);
		model.addAttribute("secteur", secteur);
		model.addAttribute("del_longueur", true);
		return "choisirvoie_delete";
	}
	
	

	@PostMapping("/supprimer/site/secteur/voie/longueur/{id}")
	public String SupprimerLongueur_voie(@PathVariable("id") Integer id, Model model
			, HttpSession session, Principal principal
			, HttpServletRequest request, String nomVoie) {
		

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("nom voie récupéré à supprimer*: " + nomVoie);
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		Voie voie = voieRepo.findByNom(nomVoie);
		model.addAttribute("voie", voie);
		model.addAttribute("nomLongueur", new String());
		
		return "choisirlong_delete";
	}
	
	@PostMapping("/supprimer/longueur/longueur/site/{id}")
	public String suppressionLongueur(@PathVariable("id") Integer id, Model model
			, HttpSession session, Principal principal
			, HttpServletRequest request, String nomLongueur) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("nom longueur récupéré à supprimer*: " + nomLongueur);
		
		Longueur longueur = longueurRepo.findByNom(nomLongueur);
		longueurRepo.delete(longueur);
		
		return "espace";
		
	}

}
