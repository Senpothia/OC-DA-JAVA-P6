package com.formation.escalade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.GeneralService;
import com.formation.escalade.service.SiteService;

@Controller
public class SiteController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;

	final int LIGNE = 5; // nombre de site afficher par ligne de la galerie

	@Autowired
	SiteService siteService;

	@Autowired
	GeneralService generalService;

	public SiteController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
	}

	@GetMapping("/creationsite")
	public String creationSite(Model model, HttpServletRequest request,
			Principal principal) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		model.addAttribute("formSite", new FormSite());

		return "creation_site";
	}

	@PostMapping("/creationsite")

	public String siteSubmit(FormSite formSite, HttpServletRequest request,
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
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		siteService.createSite(formSite, utilisateur);

		return "creation_site";
	}

	@GetMapping("/annuler")
	public String annulation(HttpServletRequest request, Model model,
			Principal principal) {
		System.out.println("entrée dans annulation()");
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		return "espace";
	}

	@GetMapping("/structure/site/{id}")
	public String structureSite(@PathVariable("id") Integer id, Model model,HttpServletRequest request,
			Principal principal) {
		
		System.out.println("entrée structureSite()");

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
		System.out.println("Nom du site: " + site.getNom());
		model.addAttribute("site", site);
		return "structure";
	}

	@GetMapping("/modifier/site/{id}")
	public String modifierSite(@PathVariable("id") Integer id, Model model,
			HttpServletRequest request, Principal principal) {

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
		return "arbre";
	}

	@GetMapping("/choisirsite")
	public String choisirSite(Model model, HttpServletRequest request,
			Principal principal) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);
		return "choisirsite";
	}

	@PostMapping("/choisirsite")
	public String choixSite(String nomSite, Model model, HttpServletRequest request) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Site site = siteRepo.findByNom(nomSite);
		model.addAttribute("site", site);

		return "arbre";
	}

	@GetMapping("/galerie/{page}")
	public String galeriePage(@PathVariable("page") int page, Model model, HttpServletRequest request,
			Principal principal) {
		System.out.println("entrée /galerie");

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		List<Site> sites = siteRepo.findAll();
		if (sites.size()>0) {
			
			generalService.pagination(page, model);
			return "galerie";

		} else return "galerie0";

		
	}

	@GetMapping("/viewsite/{nomSite}")
	public String vueSite(@PathVariable("nomSite") String nomSite, Model model, HttpServletRequest request,
			Principal principal) {

		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}

		Site site = siteRepo.findByNom(nomSite);
		model.addAttribute("site", site);
		Integer idCreateur = site.getCreateur();
		Utilisateur createur = utilisateurRepo.getOne(idCreateur);
		model.addAttribute("createur", createur);

		return "site";

	}

	@GetMapping("/commentaires/site/{id}")
	public String getComments(@PathVariable("id") Integer id, Model model, HttpServletRequest request, Principal principal) {
		System.out.println("entrée getComments()");
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
		List<Commentaire> commentaires = site.getCommentaires();
		model.addAttribute("commentaires", commentaires);
		model.addAttribute("site", site);
		
		return "commentaires";

	}

	@GetMapping("/commenter/site/{id}")
	public String commenter(@PathVariable("id") Integer id, Model model, 
			HttpServletRequest request, HttpSession session) {
		
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
		session.setAttribute("IDSITE", id);
		model.addAttribute("site", site);
		

		return "commenter";

	}

	@PostMapping("/commenter")
	public String saveComment(String comment, HttpServletRequest request, Model model) {
		
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		System.out.println("Commentaire reçu:" + comment);
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		System.out.println("site id: " + siteId);
		Commentaire commentaire = new Commentaire();
		Utilisateur auteur = new Utilisateur();
		String email = request.getUserPrincipal().getName();
		auteur = utilisateurRepo.findByEmail(email);
		//auteur = utilisateurRepo.getOne(1);
		commentaire.setAuteur(auteur);
		Site site = siteRepo.getOne(siteId);
		commentaire.setSite(site);
		commentaire.setText(comment);
		commentaireRepo.save(commentaire);
		model.addAttribute("site", site);
		
		return "site";
	}

	@GetMapping("/commentaire/supprimer/comment")
	public String supprimerCommentaire(@RequestParam("siteId") Integer siteId, 
			@RequestParam("num") int num, HttpServletRequest request, Model model) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Site site = siteRepo.getOne(siteId);
		model.addAttribute(site);
		List<Commentaire> commentaires = site.getCommentaires();
		Commentaire commentaire = commentaires.get(num);
		System.out.println(commentaire.getText());
		commentaireRepo.delete(commentaire);
		return "site";
	}

	@GetMapping("/commentaire/modifier/comment")
	public String modifierCommentaire(@RequestParam("siteId") Integer siteId, 
			@RequestParam("num") int num, Model model,
			HttpSession session, HttpServletRequest request) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		session.setAttribute("IDSITE", siteId);
		Site site = siteRepo.getOne(siteId);
		List<Commentaire> commentaires = site.getCommentaires();
		Commentaire commentaire = commentaires.get(num);
		System.out.println(commentaire.getText());
		model.addAttribute("commentaire", commentaire);
		String comment = commentaire.getText();
		session.setAttribute("COMMENT", commentaire);
		model.addAttribute("site", site);
		model.addAttribute("comment", comment);
		return "modifiercommentaire";
	}

	@PostMapping("/commentaire/modification")
	public String modificationCommentaire(String comment, HttpServletRequest request, 
			Model model) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Integer siteId = (Integer) request.getSession().getAttribute("IDSITE");
		Site site = siteRepo.getOne(siteId);
		model.addAttribute(site);
		Commentaire commentaire = (Commentaire) request.getSession().getAttribute("COMMENT");
		System.out.println(comment);
		commentaire.setText(comment);
		commentaireRepo.save(commentaire);
		return "site";
	}

	@GetMapping("/modifier/site")
	public String modifierSite(HttpSession session,HttpServletRequest request
			, Principal principal, Model model) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);
		return "choisirsite";
	}
	
	@GetMapping("/modifier/informations/site/{id}")
	public String modifierSiteInfos(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request
			, Principal principal, Model model) {
		
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
		FormSite formSite = new FormSite();
		formSite.setIdSite(id);
		formSite.setNomSite(site.getNom());
		formSite.setLocalisationSite(site.getLocalisation());
		formSite.setDepartementSite(site.getDepartement());
		model.addAttribute("formSite", formSite);
		return "modifier_infos";
	}
	
	
	@PostMapping("/modification/informations/{id}")
	public String informations(@PathVariable("id") Integer id ,FormSite formSite, HttpServletRequest request, 
			Model model) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("Méthode post traitement modification informations site");
		System.out.println("Id site: " + id);
		System.out.println(formSite.toString());
		
		Site site = siteRepo.getOne(id);
		site.setNom(formSite.getNomSite());
		site.setLocalisation(formSite.getLocalisationSite());
		site.setDepartement(formSite.getDepartementSite());
		siteRepo.save(site);
		
		return "espace";
	}

	@GetMapping("/modifier/secteur/site/{id}")
	public String modifierSecteur(@PathVariable("id") Integer id, HttpSession session
			,HttpServletRequest request
			, Principal principal, Model model) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		
		session.setAttribute("IDSITE", id);
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		String nomSecteur = new String();
		model.addAttribute("nomSite", nomSecteur);
		return "choisir_secteur_modification";
		
	}
	
	@PostMapping("/modifier/secteur/site/{id}")
	public String choixSecteurModification(@PathVariable("id") Integer id,Model model, HttpServletRequest request
			, Principal principal, String nomSecteur) {
		
		System.out.println("Entrée post choixSecteurModification");
		System.out.println("nom secteur récupéré: " + nomSecteur );
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		FormSite formSite = new FormSite();
		formSite.setIdSite(id);
		formSite.setNomSecteur(secteur.getNom());   // Nom de secteur à modifier
		model.addAttribute("formSite", formSite);
		
		return "modifier_infos_secteur";
	}
	
	@PostMapping("/modification/informations/secteur/{ancienNomSecteur}")
	public String modifierInfosSecteur(@PathVariable("ancienNomSecteur") String ancienNomSecteur, 
			String nomSecteur, HttpSession session
			,HttpServletRequest request, Principal principal
			, Model model) {
		
		// Methode de traitement du changement de nom du secteur
		System.out.println("Méthode changement de nom pour un secteur");
		System.out.println("Ancien nom de secteur récupéré: " + ancienNomSecteur);
		System.out.println("Nouveau nom de secteur récupéré: " + nomSecteur);
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Secteur secteur = secteurRepo.findByNom(ancienNomSecteur);
		secteur.setNom(nomSecteur);
		secteurRepo.save(secteur);
		return "espace";
	}
	

	@GetMapping("/modifier/voie/site/{id}")
	public String modifierVoie(@PathVariable("id") Integer id, HttpSession session
			,HttpServletRequest request, Principal principal, Model model) {
		
		
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
		String nomSecteur = new String ();
		model.addAttribute("nomSecteur", nomSecteur);
		return "choisirsecteur_voie";
	}
	
	
	@PostMapping("/modifier/voie/site/{id}")
	public String modifierVoieSecteur(@PathVariable("id") Integer id, 
			String nomSecteur,HttpSession session
			,HttpServletRequest request, Principal principal, Model model ) {
		
		
		System.out.println("Méthode modifierVoieSecteur");
		System.out.println("id site:" + id);
		System.out.println("nom de secteur récupéré: " + nomSecteur);
		
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
		String nomVoie = new String();
		model.addAttribute("nomVoie", nomVoie);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		return "modification_choisirvoie";
	}
	
	@PostMapping("/modifier/voie/site/voie/{id}")
	public String ModificationVoie(@PathVariable("id") Integer id, 
			String nomVoie,HttpSession session
			,HttpServletRequest request, Principal principal, Model model ) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("Méthode modificationVoie");
		System.out.println("id site:" + id);
		System.out.println("nom de voie récupéré: " + nomVoie);
		
		Voie voie = voieRepo.findByNom(nomVoie);
		
		FormSite formSite = new FormSite();
		formSite.setIdSite(id);
		formSite.setNomVoie(nomVoie);
		formSite.setCotationVoie(voie.getCotation());
		
		model.addAttribute("formSite", formSite);
		return "modifier_infos_voie";
	}
	
	@PostMapping("/modification/informations/voie/{nomVoie}")
	public String modificationInfosVoie(@PathVariable("nomVoie") String nomVoie 
			,HttpSession session, FormSite formSite
			,HttpServletRequest request, Principal principal, Model model ) {
		
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
		voie.setNom(formSite.getNomVoie());
		voie.setCotation(formSite.getCotationVoie());
		voieRepo.save(voie);
		return "espace";
		
	}
	
	@GetMapping("/modifier/longueur/site/{id}")
	public String modifierLongueur(@PathVariable("id") Integer id, HttpSession session
			,HttpServletRequest request, Principal principal, Model model) {
		
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
		
		
		return "choisirsecteur_long";
	}
	
	@PostMapping("/modifier/longueur/site/{id}")
	public String modifierLongSecteur(@PathVariable("id") Integer id
			, HttpSession session
			,HttpServletRequest request, Principal principal
			, Model model,String nomSecteur) {
		
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
		return "choisirvoie_long";
		
	}
	
	@PostMapping("/modifier/longueur/voie/site/{id}")
	public String modifierLongSecteurVoie(@PathVariable("id") Integer id
			, HttpSession session
			,HttpServletRequest request, Principal principal
			, Model model,String nomVoie) {
		
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
		
		
		return "choisirlong_long";
	}
	
	@PostMapping("/modifier/longueur/longueur/site/{id}")
	public String modifierLongSecteurVoieFinalisation(@PathVariable("id") Integer id
			, HttpSession session
			,HttpServletRequest request, Principal principal
			, Model model,String nomLongueur) {
		
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
		
		return "modifier_infos_long";
	}
	
	

	@PostMapping("/modification/informations/longueur/{nomLongueur}")
	public String modifierInfosLongueur(@PathVariable("nomLongueur") String nomLongueur
			,HttpSession session
			,HttpServletRequest request, Principal principal
			, FormSite formSite, Model model) {
		
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
		
		return "espace";
	}
	
	@GetMapping("/officialiser/{id}")
	public String officialiser(@PathVariable("id") Integer id, HttpSession session
			,HttpServletRequest request, Principal principal, Model model) {
		
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
		Boolean officiel = site.isOfficiel();
		site.setOfficiel(!officiel);
		siteRepo.save(site);
		return "espace";
	}

}
