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
import com.formation.escalade.model.Demande;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Topo;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.IDemande1;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.ITopo;
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
	private final ITopo topoRepo;
	private final IDemande1 demandeRepo;
		

	final int LIGNE = 5; // nombre de site afficher par ligne de la galerie

	@Autowired
	SiteService siteService;

	@Autowired
	GeneralService generalService;

	public SiteController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo, ITopo topoRepo, IDemande1 demandeRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
		this.topoRepo = topoRepo;
		this.demandeRepo = demandeRepo;
	}

	@GetMapping("/creationsite")
	public String creationSite(Model model, HttpServletRequest request, Principal principal) {

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
		model.addAttribute("phrase", new String());
		return "creation_site";
	}

	@PostMapping("/creationsite")

	public String siteSubmit(FormSite formSite, HttpServletRequest request, Principal principal, Model model) {

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
		Boolean creation = siteService.createSite(formSite, utilisateur);
		if (creation) {
		model.addAttribute("phrase", new String());
		
		return "espace";
		
		} else {
			
			model.addAttribute("erreur", true);
			return "creation_site";
		}
	}

	@GetMapping("/annuler")
	public String annulation(HttpServletRequest request, Model model, Principal principal) {
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
		
		model.addAttribute("phrase", new String());
		return "espace";
	}

	@GetMapping("/structure/site/{id}")
	public String structureSite(@PathVariable("id") Integer id, Model model, HttpServletRequest request,
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
		model.addAttribute("phrase", new String());
		return "structure";
	}

	@GetMapping("/modifier/site/{id}")
	public String modifierSite(@PathVariable("id") Integer id, Model model, HttpServletRequest request,
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
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		model.addAttribute("phrase", new String());
		Boolean[] arePresent = siteService.decomposerSite(id);
		model.addAttribute("noSecteur", arePresent[0]);
		model.addAttribute("noVoie", arePresent[1]);
		model.addAttribute("noLongueur", arePresent[2]);
		return "arbre";
	}

	@GetMapping("/choisirsite")
	public String choisirSite(Model model, HttpServletRequest request, Principal principal) {

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
		Integer id =utilisateur.getId();
		//List<Site> sites = siteRepo.findAll();
		List<Site> sites = siteRepo.findByCreateur(id);
		
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);
		model.addAttribute("phrase", new String());
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
		Integer id = site.getId();
		model.addAttribute("site", site);
		model.addAttribute("phrase", new String());
		Boolean[] arePresent = siteService.decomposerSite(id);
		System.out.println("Depuis siteControler(), arePresent0: " + arePresent[0] );
		System.out.println("Depuis siteControler(), arePresent1: " + arePresent[1] );
		System.out.println("Depuis siteControler(), arePresent2: " + arePresent[2] );
		model.addAttribute("noSecteur", arePresent[0]);
		model.addAttribute("noVoie", arePresent[1]);
		model.addAttribute("noLongueur", arePresent[2]);
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
		if (sites.size() > 0) {

			generalService.pagination(page, model);
			return "galerie";

		} else
			model.addAttribute("phrase", new String());
			return "galerie0";

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
		System.out.println("Sortie vueSite()");
		model.addAttribute("phrase", new String());
		return "site";

	}

	@GetMapping("/commentaires/site/{id}")
	public String getComments(@PathVariable("id") Integer id, Model model, HttpServletRequest request,
			Principal principal) {
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
		Boolean vide = false;
		if (commentaires.size() == 0) {
			
			vide = true;
			
		}
		model.addAttribute("vide", vide);
		model.addAttribute("commentaires", commentaires);
		model.addAttribute("site", site);
		model.addAttribute("phrase", new String());
		return "commentaires";

	}

	@GetMapping("/commenter/site/{id}")
	public String commenter(@PathVariable("id") Integer id, Model model, HttpServletRequest request,
			HttpSession session) {

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
		Integer idCreateur = site.getCreateur();
		Utilisateur createur = utilisateurRepo.getOne(idCreateur);
		model.addAttribute("createur", createur);
		model.addAttribute("site", site);
		model.addAttribute("phrase", new String());
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
		// auteur = utilisateurRepo.getOne(1);
		commentaire.setAuteur(auteur);
		Site site = siteRepo.getOne(siteId);
		commentaire.setSite(site);
		commentaire.setText(comment);
		commentaireRepo.save(commentaire);
		model.addAttribute("site", site);
		Integer idCreateur = site.getCreateur();
		Utilisateur createur = utilisateurRepo.getOne(idCreateur);
		model.addAttribute("createur", createur);
		model.addAttribute("phrase", new String());
		return "site";
	}

	@GetMapping("/commentaire/supprimer/comment")
	public String supprimerCommentaire(@RequestParam("siteId") Integer siteId, @RequestParam("num") int num,
			HttpServletRequest request, Model model) {

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
		Integer idCreateur = site.getCreateur();
		Utilisateur createur = utilisateurRepo.getOne(idCreateur);
		model.addAttribute("createur", createur);
		model.addAttribute("phrase", new String());
		return "site";
	}

	@GetMapping("/commentaire/modifier/comment")
	public String modifierCommentaire(@RequestParam("siteId") Integer siteId, @RequestParam("num") int num, Model model,
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
		model.addAttribute("phrase", new String());
		return "modifiercommentaire";
	}

	@PostMapping("/commentaire/modification")
	public String modificationCommentaire(String comment, HttpServletRequest request, Model model) {

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
		Integer idCreateur = site.getCreateur();
		Utilisateur createur = utilisateurRepo.getOne(idCreateur);
		model.addAttribute("createur", createur);
		model.addAttribute("phrase", new String());
		return "site";
	}

	@GetMapping("/modifier/site")
	public String modifierSite(HttpSession session, HttpServletRequest request, Principal principal, Model model) {

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
		model.addAttribute("phrase", new String());
		return "choisirsite";
	}

	@GetMapping("/modifier/informations/site/{id}")
	public String modifierSiteInfos(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
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
		FormSite formSite = new FormSite();
		formSite.setIdSite(id);
		formSite.setNomSite(site.getNom());
		formSite.setLocalisationSite(site.getLocalisation());
		formSite.setDepartementSite(site.getDepartement());
		model.addAttribute("formSite", formSite);
		model.addAttribute("phrase", new String());
		return "modifier_infos";
	}

	@PostMapping("/modification/informations/{id}")
	public String informations(@PathVariable("id") Integer id, FormSite formSite, HttpServletRequest request,
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
		model.addAttribute("phrase", new String());
		return "espace";
	}

	@GetMapping("/modifier/secteur/site/{id}")
	public String modifierSecteur(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
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

		session.setAttribute("IDSITE", id);
		Site site = siteRepo.getOne(id);
		model.addAttribute("site", site);
		String nomSecteur = new String();
		model.addAttribute("nomSite", nomSecteur);
		model.addAttribute("phrase", new String());
		return "choisir_secteur_modification";

	}

	@PostMapping("/modifier/secteur/site/{id}")
	public String choixSecteurModification(@PathVariable("id") Integer id, Model model, HttpServletRequest request,
			Principal principal, String nomSecteur) {

		System.out.println("Entrée post choixSecteurModification");
		System.out.println("nom secteur récupéré: " + nomSecteur);
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
		formSite.setNomSecteur(secteur.getNom()); // Nom de secteur à modifier
		model.addAttribute("formSite", formSite);
		model.addAttribute("phrase", new String());
		return "modifier_infos_secteur";
	}

	@PostMapping("/modification/informations/secteur/{ancienNomSecteur}")
	public String modifierInfosSecteur(@PathVariable("ancienNomSecteur") String ancienNomSecteur, String nomSecteur,
			HttpSession session, HttpServletRequest request, Principal principal, Model model) {

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
		model.addAttribute("phrase", new String());
		return "espace";
	}

	@GetMapping("/modifier/voie/site/{id}")
	public String modifierVoie(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
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
		return "choisirsecteur_voie";
	}

	@PostMapping("/modifier/voie/site/{id}")
	public String modifierVoieSecteur(@PathVariable("id") Integer id, String nomSecteur, HttpSession session,
			HttpServletRequest request, Principal principal, Model model) {

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
		model.addAttribute("phrase", new String());
		return "modification_choisirvoie";
	}

	@PostMapping("/modifier/voie/site/voie/{id}")
	public String ModificationVoie(@PathVariable("id") Integer id, String nomVoie, HttpSession session,
			HttpServletRequest request, Principal principal, Model model) {

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
		model.addAttribute("phrase", new String());
		return "modifier_infos_voie";
	}

	@PostMapping("/modification/informations/voie/{nomVoie}")
	public String modificationInfosVoie(@PathVariable("nomVoie") String nomVoie, HttpSession session, FormSite formSite,
			HttpServletRequest request, Principal principal, Model model) {

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
		model.addAttribute("phrase", new String());
		return "espace";

	}

	
	@GetMapping("/officialiser/{id}")
	public String officialiser(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
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
		Boolean officiel = site.isOfficiel();
		site.setOfficiel(!officiel);
		siteRepo.save(site);
		model.addAttribute("phrase", new String());
		return "espace";
	}

	@GetMapping("/supprimer/site/{id}")
	public String supprimerSite(@PathVariable("id") Integer id, HttpSession session, HttpServletRequest request,
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
		List<Commentaire> commentaires = site.getCommentaires();
		for (Commentaire commentaire : commentaires) {

			commentaireRepo.delete(commentaire);
		}
		
		List<Topo> topos = site.getTopos();
		for (Topo topo : topos) {
			Integer idTopo = topo.getId();
			List<Demande> demandes = demandeRepo.findByTopoId(idTopo);
			demandeRepo.deleteAll(demandes);
			topoRepo.delete(topo);
		}

		List<Secteur> secteurs = site.getSecteurs();
		List<Voie> voies = new ArrayList<Voie>();
		List<Longueur> longueurs = new ArrayList<Longueur>();

		for (Secteur secteur : secteurs) {

			List<Voie> voies1 = secteur.getVoies();
			for (int i = 0; i < voies1.size(); i++) {

				voies.add(voies1.get(i));
			}
		}

		for (Voie voie : voies) {

			List<Longueur> longueur1 = voie.getLongueurs();
			for (int i = 0; i < longueur1.size(); i++) {

				longueurs.add(longueur1.get(i));
			}
		}

		for (int i = 0; i < longueurs.size(); i++) {

			longueurRepo.delete(longueurs.get(i));

		}

		for (int i = 0; i < voies.size(); i++) {

			voieRepo.delete(voies.get(i));

		}
		
		for (int i = 0; i < secteurs.size(); i++) {

			secteurRepo.delete(secteurs.get(i));

		}

		siteRepo.delete(site);
		model.addAttribute("phrase", new String());
		return "espace";
	}
	
	@GetMapping("/informations")
	public String informations(@RequestParam("nomSite") String nomSite
			, @RequestParam("nomSecteur") String nomSecteur
			, @RequestParam("nomVoie") String nomVoie
			, @RequestParam("nomLongueur") String nomLongueur
			, HttpSession session, HttpServletRequest request
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
		
		
		Boolean isSecteur = false;
		Boolean isVoie = false;
		Boolean isLongueur = false;
		
		Site site = siteRepo.findByNom(nomSite);
		model.addAttribute("site", site);
		Integer idCreateur = site.getCreateur();
		Utilisateur createur = utilisateurRepo.getOne(idCreateur);
		model.addAttribute("createur", createur);
		Secteur secteur = secteurRepo.findByNom(nomSecteur);
		model.addAttribute("secteur", secteur);
		
		if (nomVoie.equals("0") && nomLongueur.equals("0")) {
			
			isSecteur = true;
		}
		
		if (!nomVoie.equals("0") && nomLongueur.equals("0")) {
			
			isVoie = true;
			Voie voie = voieRepo.findByNom(nomVoie);
			model.addAttribute("voie", voie);
			
		} 
		
		if (!nomLongueur.equals("0")){
			
			isLongueur = true;
			Longueur longueur = longueurRepo.findByNom(nomLongueur);
			model.addAttribute("longueur", longueur);
		}
		
		
		model.addAttribute("isSecteur", isSecteur);
		model.addAttribute("isVoie", isVoie);
		model.addAttribute("isLongueur", isLongueur);
		model.addAttribute("phrase", new String());
		return "structure_details";
	}
	

}
