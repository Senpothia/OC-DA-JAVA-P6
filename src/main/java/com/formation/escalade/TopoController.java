package com.formation.escalade;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.escalade.model.Commentaire;

import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.FormTopo;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Topo;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.ICommentaire;

import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.ITopo;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.GeneralService;
import com.formation.escalade.service.SiteService;

@Controller
public class TopoController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
	private final ITopo topoRepo;
	


	public TopoController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo, ITopo topoRepo ) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
		this.topoRepo = topoRepo;
		
	}

	@GetMapping("/topo/creation")   // A partir de l'espace personnel
	public String creationTopo(Model model, HttpServletRequest request
			, Principal principal) {
		
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
		model.addAttribute("formTopo", new FormTopo());
		model.addAttribute("listeSite", true);  // Avec affichage de la liste des sites
		return "creation_topo";
	}
	
	
	@GetMapping("/topo/creation/{nomSite}") // A partir de la page d'un site
	public String creationTopoPageTopos(@PathVariable ("nomSite") String nomSite, Model model 
			, HttpServletRequest request, Principal principal, HttpSession session) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		session.setAttribute("NOMSITE", nomSite);
		
		System.out.println("creationTopoPageTopos, nom du site: " + nomSite);
		FormTopo formTopo = new FormTopo();
		formTopo.setNomSite(nomSite);
		model.addAttribute("formTopo", formTopo);
		//model.addAttribute("nomSite", nomSite);
		model.addAttribute("listeSite", false);  // sans affichage de la liste des site
		return "creation_topo";
		
	}

	@PostMapping("/topo/creation")      // Enregistrement topo crée
	public String retourFormTopo(FormTopo formTopo, HttpServletRequest request
			,Model model,Principal principal) {
		
		System.out.println("Entrée retourFormTopo - POST");
		System.out.println("Nom du site:" + formTopo.getNomSite());
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println(formTopo.toString());
		Topo topo = new Topo();
		topo.setNom(formTopo.getNom());
		topo.setDescription(formTopo.getDescription());
		topo.setLieu(formTopo.getLieu());
		topo.setDate(formTopo.getDate());
		topo.setDisponible(formTopo.isDisponibilite());
		
		try {
		Site site = siteRepo.findByNom(formTopo.getNomSite());
		Integer id = site.getId();
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		topo.setProprietaire(utilisateur);
		topo.setSite(site);
		topoRepo.save(topo);
		
		} catch(NullPointerException e){
			
			System.out.println("traitement exception");
			String nomSite = (String) request.getSession().getAttribute("NOMSITE");
			System.out.println("nom du site récupéré par session: " + nomSite);
			Site site = siteRepo.findByNom(nomSite);
			Integer id = site.getId();
			String email = request.getUserPrincipal().getName();
			Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
			topo.setProprietaire(utilisateur);
			topo.setSite(site);
			topoRepo.save(topo);
			
		}
		
		return "espace";
	}
	
	/**
	@GetMapping("/choisirtopo")  // Sélection site pour visualisation des topos du site
	public String selectionSiteTopo(Model model) {

		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);

		return "choisirtopo";

	}

	@PostMapping("/choisirtopo")   // Liste des topos pour un site
	public String choixSite(String nomSite, Model model) {
		
		System.out.println(nomSite);
		Site site = siteRepo.findByNom(nomSite);
		Integer id_site = site.getId();
		List<Topo> topos = topoRepo.findBySite(site);
		List<String> noms = new ArrayList<>();
		List<String> prenoms = new ArrayList<>();
		
		for (Topo topo:topos ) {
			Utilisateur proprietaire = topo.getProprietaire();
			prenoms.add(proprietaire.getPrenom());
			noms.add(proprietaire.getNom());
		}
			for (int i = 0; i < noms.size(); i++) {

			System.out.println("nom: " + noms.get(i));
		}
			
			for (int i = 0; i < prenoms.size(); i++) {

				System.out.println("prenom: " + prenoms.get(i));
			}

		model.addAttribute("noms", noms);
		model.addAttribute("prenoms", prenoms);
		model.addAttribute("topos", topos);
		model.addAttribute("site", site);

		return "topos";
	}
	*/
	
	@GetMapping("/reservation/topo")  // Reservation à partir de la liste des topos du site
	public String reservation(@RequestParam("siteId") Integer siteId, @RequestParam("num") int num
			, Model model,
			HttpSession session , HttpServletRequest request){
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		System.out.println("Id site: " + siteId);
		System.out.println("num top: " + num);
		Site site = siteRepo.getOne(siteId);
		List<Topo> topos = topoRepo.findBySite(site);
		Topo topo = topos.get(num);	
		/*
		Demande demande = new Demande();
		demande.setDemandeur(utilisateurRepo.getOne(1));
		demande.setTopo(topo);
		demandeRepo.save(demande);
		*/
		return "ok";
	}
	
	@GetMapping("/reservation/liste/{id}")
	public String listerReservation (@PathVariable("id") Integer id, Model model){
		
		Site site = siteRepo.getOne(id);
		
		//System.out.println("Nbre de demande" + demandes.size());
	
		return "ok";
	}
	
	@GetMapping("topos/site/{id}")  // accès à liste topo depuis la page de site
	public String listeTopos(@PathVariable("id") Integer id, HttpServletRequest request
			,Model model,Principal principal) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: *****" + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			Boolean authentification = true;
			model.addAttribute("authentification", authentification);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Site site = siteRepo.getOne(id);
		System.out.println("Visu page topo site: " + id);
		
		Integer id_site = site.getId();
		List<Topo> topos = topoRepo.findBySite(site);
		List<String> noms = new ArrayList<>();
		List<String> prenoms = new ArrayList<>();
		
		for (Topo topo:topos ) {
			Utilisateur proprietaire = topo.getProprietaire();
			prenoms.add(proprietaire.getPrenom());
			noms.add(proprietaire.getNom());
			
		}
		
			for (int i = 0; i < noms.size(); i++) {

			System.out.println("nom: " + noms.get(i));
		}
			
			for (int i = 0; i < prenoms.size(); i++) {

				System.out.println("prenom: " + prenoms.get(i));
			}


		model.addAttribute("noms", noms);
		model.addAttribute("prenoms", prenoms);
		model.addAttribute("topos", topos);
		model.addAttribute("site", site);

		return "topos";
	}
	
	@GetMapping("/modifier/topo")
	public String modifierTopo(@RequestParam("siteId") Integer siteId, @RequestParam("num") int num, Model model,
			HttpSession session){
		
		return "ok";
	}
	
	@GetMapping("/topo/personnelles")
	public String mesTopos(HttpServletRequest request
			,Model model,Principal principal) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré:" + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			Boolean authentification = true;
			model.addAttribute("authentification", authentification);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		List<Topo> topos = utilisateur.getTopos();
		
		System.out.println("Nombre de topos: " + topos.size());
		System.out.println("Topo 1: " + topos.get(0));
		
		model.addAttribute("topos", topos);
		
		
		
		return "liste_topos";
	}
	
	@GetMapping("/topo/status")
	public String modifierStatusTopo(@RequestParam("num") int num , HttpServletRequest request
			,Model model,Principal principal){
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré:" + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			Boolean authentification = true;
			model.addAttribute("authentification", authentification);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		List<Topo> topos = utilisateur.getTopos();
		Topo topo = topos.get(num);
		topo.setDisponible(!topo.isDisponible());
		topoRepo.save(topo);
		return "espace";
	}
	
	@GetMapping("/topo/supprimer")
	public String supprimerTopo(@RequestParam("num") int num , HttpServletRequest request
			,Model model,Principal principal){
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré:" + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			Boolean authentification = true;
			model.addAttribute("authentification", authentification);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		List<Topo> topos = utilisateur.getTopos();
		Topo topo = topos.get(num);
		topoRepo.delete(topo);
		return "espace";
	}
}
