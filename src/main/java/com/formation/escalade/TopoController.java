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
import com.formation.escalade.model.Demande1;
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
public class TopoController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
	private final ITopo topoRepo;
	private final IDemande1 demande1Repo;
	


	public TopoController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo, ITopo topoRepo, IDemande1 demande1Repo ) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
		this.topoRepo = topoRepo;
		this.demande1Repo = demande1Repo;
		
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

	@PostMapping("/topo/creation")      // Enregistrement topo créée
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
	
	
	
	@GetMapping("/topo/emprunts")  // Visualisation des topos empruntées
	public String reservation(Model model,
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
		
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
	
		
		List<Demande1> demandes = utilisateur.getDemandes1();
		List<Topo> topos = new ArrayList<>();
		for (Demande1 demande: demandes) {
			boolean emprunt = demande.getAcceptee();
			if (emprunt) {
			Topo topo = demande.getTopo();
			 topos.add(topo);
			}
			
		}
		
		int taille = topos.size();
		Boolean vide = false;
		if (taille == 0) { 
			vide = true;
		}
		
		model.addAttribute("vide", vide);
		model.addAttribute("topos", topos);
		
		return "liste_emprunts";
	}
	
	@GetMapping("/topo/reserver")   // Reserver une topo
	public String listerReservation (@RequestParam("siteId") Integer siteId
			, @RequestParam("num") int num
			, Model model , HttpServletRequest request){
		
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
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		
		Demande1 demande1 = new Demande1();
		demande1.setDemandeur(utilisateur);
		demande1.setAcceptee(false);
		demande1.setActive(true);
		
		Site site = siteRepo.getOne(siteId);
		List<Topo> topos = site.getTopos();
		Topo topo = topos.get(num);
		
		demande1.setTopo(topo);
		demande1Repo.save(demande1);
		
		return "espace";
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

			int taille = topos.size();
			Boolean vide = false;
			if (taille == 0) { 
				vide = true;
			}
			
		model.addAttribute("vide", vide);
			
		model.addAttribute("noms", noms);
		model.addAttribute("prenoms", prenoms);
		model.addAttribute("topos", topos);
		model.addAttribute("site", site);

		return "topos";
	}
	
	@GetMapping("/topo/modifier")      // Modification d'une topo - Get
	public String modifierTopo(@RequestParam("num") int num, HttpServletRequest request
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
		Site site = topo.getSite();
		
		FormTopo formTopo = new FormTopo();
		formTopo.setNom(topo.getNom());
		formTopo.setDescription(topo.getDescription());
		formTopo.setLieu(topo.getLieu());
		formTopo.setDate(topo.getDate());
		formTopo.setDisponibilite(topo.isDisponible());
		
		model.addAttribute("formTopo", formTopo);
		model.addAttribute("num", num);
		
		return "modification_topo";
	}
	
	@PostMapping("/topo/modifier/{num}")    // Modification d'une topo - Post
	public String modifierTopoPost(@PathVariable("num") int num, HttpServletRequest request
			,Model model,Principal principal, FormTopo formTopo) {
		
		
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
		
		System.out.println("Méthode modif topo - post");
		System.out.println("num récupéré: " + num);
		
		String email = request.getUserPrincipal().getName();
		Utilisateur utilisateur = utilisateurRepo.findByEmail(email);
		List<Topo> topos = utilisateur.getTopos();
		
		Topo topo = topos.get(num);
		topo.setNom(formTopo.getNom());
		topo.setDescription(formTopo.getDescription());
		topo.setLieu(formTopo.getLieu());
		topo.setDisponible(formTopo.isDisponibilite());
		topoRepo.save(topo);
		
		return "espace";
	}
	
	@GetMapping("/topo/personnelles")   // Accès à la liste des topos personnelles
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
		
		//System.out.println("Nombre de topos: " + topos.size());
		//System.out.println("Topo 1: " + topos.get(0));
		
		int taille = topos.size();
		Boolean vide = false;
		if (taille == 0) { 
			vide = true;
		}
		
		model.addAttribute("vide", vide);
		model.addAttribute("topos", topos);
		
		return "liste_topos";
	}
	
	@GetMapping("/topo/status")   // Changement du status d'une topo (dispo / indisponible)
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
	
	@GetMapping("/topo/supprimer")   // Suppression des topos
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
	
	@GetMapping("/topo/demandes/faites")        // Visualisation des demandes de topos adressée aux propriétaires
	public String demandesFaites(HttpServletRequest request
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
		List<Demande1> demandes = utilisateur.getDemandes1();
		List<Topo> topos = new ArrayList<>();
		
		for (Demande1 demande : demandes) {
			
			topos.add(demande.getTopo());
			
		}
		
		int taille = topos.size();
		Boolean vide = false;
		if (taille == 0) { 
			vide = true;
		}
		
		model.addAttribute("vide", vide);
		
		model.addAttribute("topos" , topos);
		return "liste_demandes";
	}
	
	@GetMapping("/topo/demandes/recues")     // Visualisation des demandes de topos reçues
	public String demandesRecus(HttpServletRequest request
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
		Integer idUser = utilisateur.getId();
		List<Demande1> demandes = demande1Repo.findAll();
		List<Topo> topos = new ArrayList<>();
		for (Demande1 demande: demandes) {
			
			Topo topo = demande.getTopo();
			Utilisateur user = topo.getProprietaire();
			Integer id = user.getId();
			if (id == idUser) {
				
				topos.add(topo);
			}
			
		}
		
		int taille = topos.size();
		Boolean vide = false;
		if (taille == 0) { 
			vide = true;
		}
		
		model.addAttribute("vide", vide);
		model.addAttribute("topos", topos);
		return "demandes_recues";
	}
	
	
}
