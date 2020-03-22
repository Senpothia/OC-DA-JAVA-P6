package com.formation.escalade;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.FormCompte;
import com.formation.escalade.model.FormUser;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.repository.ICommentaire;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;

@Controller
public class AdminController {
	
	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final ICommentaire commentaireRepo;
	private final IUtilisateur utilisateurRepo;
	
	public AdminController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			ICommentaire commentaireRepo, IUtilisateur utilisateurRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
		this.utilisateurRepo = utilisateurRepo;
	}
	
	@GetMapping("/administration")
	public String admin(Model model, HttpServletRequest request,
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
		
		model.addAttribute("formUser", new FormUser());
		model.addAttribute("echec", false);
		return "gestion_user";
	}
	
	@PostMapping("/gestion/user")
	public String gestionUser(Model model, HttpServletRequest request,
			Principal principal, FormUser formUser) {
		
		System.out.println("Entrée gestionUser");
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		try {                         // Recherche par identification réussie
			
		Utilisateur user = utilisateurRepo.findByEmail(formUser.getEmail());
		System.out.println("User recupéré: " + user.getNom());
		
		FormCompte formCompte = new FormCompte();
		formCompte.setNom(user.getNom());
		formCompte.setPrenom(user.getPrenom());
		formCompte.setDepartement(user.getDepartement());
		formCompte.setEmail(user.getEmail());
		formCompte.setMembre(user.isMembre());
		
		model.addAttribute("formCompte", formCompte);
		model.addAttribute("user", user);
		
		return "modificationCompteUser";
		
		} catch(NullPointerException e) {  // Echec identification par email
			
			System.out.println("Aucun utilisateur trouvé");
			
			try {     //   Recheche par nom - prénom
				
				String nom = formUser.getNom();
				String prenom = formUser.getPrenom();
				Utilisateur user = utilisateurRepo.findByNomAndPrenom(nom, prenom);
				
				FormCompte formCompte = new FormCompte();
				formCompte.setNom(user.getNom());
				formCompte.setPrenom(user.getPrenom());
				formCompte.setDepartement(user.getDepartement());
				formCompte.setEmail(user.getEmail());
				formCompte.setMembre(user.isMembre());
				formCompte.setActif(user.isActif());
				
				model.addAttribute("formCompte", formCompte);
				model.addAttribute("user", user);
				
				return "modificationCompteUser";
			}
			
			catch(NullPointerException ex) {String email = request.getUserPrincipal().getName();
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("echec", true);
			return "gestion_user";
			}
			
		}
		
	}
	
	@PostMapping("/administration/modifier/user/{id}")
	public String modifierUser(@PathVariable("id") Integer id
			, Model model
			, HttpServletRequest request
			, Principal principal
			, FormCompte formCompte) {
		
		try {

			String email = request.getUserPrincipal().getName();
			System.out.println("email récupéré: " + email);
			model.addAttribute("utilisateur", utilisateurRepo.findByEmail(email));
			model.addAttribute("authentification", true);

		} catch (NullPointerException e) {

			System.out.println("email récupéré: aucun!!!");
			model.addAttribute("authentification", false);
		}
		
		Utilisateur user = utilisateurRepo.getOne(id);
		user.setPrenom(formCompte.getPrenom());
		user.setNom(formCompte.getNom());
		user.setDepartement(formCompte.getDepartement());
		user.setEmail(formCompte.getEmail());
		user.setMembre(formCompte.getMembre());
		user.setActif(formCompte.getActif());
		utilisateurRepo.save(user);
		
		return "espace";
	}

}
