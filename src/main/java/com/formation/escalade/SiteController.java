package com.formation.escalade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.escalade.model.Commentaire;
import com.formation.escalade.model.FormSite;
import com.formation.escalade.model.GroupeSite;
import com.formation.escalade.model.LigneSite;
import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Secteur;
import com.formation.escalade.model.Site;
import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.model.Voie;
import com.formation.escalade.repository.CommentaireRepo;
import com.formation.escalade.repository.ILongueur;
import com.formation.escalade.repository.ISecteur;
import com.formation.escalade.repository.ISite;
import com.formation.escalade.repository.IUtilisateur;
import com.formation.escalade.repository.IVoie;
import com.formation.escalade.service.SiteService;

@Controller
public class SiteController {

	private final ISite siteRepo;
	private final ISecteur secteurRepo;
	private final IVoie voieRepo;
	private final ILongueur longueurRepo;
	private final CommentaireRepo commentaireRepo;

	@Autowired
	SiteService siteService;

	public SiteController(ISite siteRepo, ISecteur secteurRepo, IVoie voieRepo, ILongueur longueurRepo,
			CommentaireRepo commentaireRepo) {

		this.siteRepo = siteRepo;
		this.secteurRepo = secteurRepo;
		this.voieRepo = voieRepo;
		this.longueurRepo = longueurRepo;
		this.commentaireRepo = commentaireRepo;
	}

	@GetMapping("/creationsite")

	public String creationSite(Model model) {

		model.addAttribute("formSite", new FormSite());

		return "creation_site";
	}

	@PostMapping("/creationsite")

	public String siteSubmit(FormSite formSite) {

		siteService.createSite(formSite);

		return "creation_site";
	}

	@GetMapping("/annuler")
	public String annulation() {

		return "index";
	}

	@GetMapping("/addsecteur")
	public String addsecteur(Model model) {
		model.addAttribute("formSite", new FormSite());
		return "add_secteur";
	}

	@GetMapping("/addvoie")
	public String addvoie() {

		return "creation_voie";
	}

	@GetMapping("/addlongueur")
	public String addlongueur() {

		return "creation_longueur";
	}

	@GetMapping("/visualisersite")
	public String visualiserSite() {

		return "index";
	}

	@GetMapping("/modifiersite")
	public String modifierSite() {

		return "index";
	}
	/**
	@GetMapping("/selection1/{id}")
	public String selection(@PathVariable("id") Integer id, Model model) {
		/**
		 * List<LigneSite> tableSite = siteService.chercherSite(id); String nomSite =
		 * new String(); nomSite = tableSite.get(0).getNomSite(); List<String>
		 * listeSecteurs = siteService.ordonnerSecteur(tableSite); List<String>
		 * listeVoies = siteService.ordonnerVoie(tableSite);
		 */
		
		/**
		Site site = siteRepo.getOne(id);
		// Traitement des secteurs
		List<Secteur> secteurs = secteurRepo.findBySite(site);  // Récupération de tous les secteurs du site
		
		for (Secteur s : secteurs) {  // Affichage de chaque secteur

			System.out.println(s.toString());
		}
		// Traitement des voies 
		List<Voie> voies = new ArrayList<Voie>();
		List<List<Voie>> listeVoies = new ArrayList<List<Voie>>();

		for (Secteur s : secteurs) {
			voies = voieRepo.findBySecteur(s);  // Récupération de toutes les voies d'un secteur
			listeVoies.add(voies);  // Une liste de voie par secteur dans une liste globale de voie (listeVoies)
		}

		for (List<Voie> v : listeVoies) {
				for (Voie w: v) {
					System.out.println(w.toString());  // Affichage de toutes les voies 
				}
			
		}

		List<Longueur> longueurs = new ArrayList<Longueur>();
		List<List<Longueur>> listeLongueurs = new ArrayList<List<Longueur>>();

		for (List<Voie> v: listeVoies) {
			
			for (Voie w: v) {
				longueurs = longueurRepo.findByVoie(w);
				listeLongueurs.add(longueurs); 
				
			}
		}
		
		for (List<Longueur> l : listeLongueurs) {
			for (Longueur j: l) {
				System.out.println(j.toString());  // Affichage de toutes les voies 
			}
		
	}
		
		siteService.decomposerSite(id);
		return "selection";

	} // fin
   */
	/**
	@GetMapping("/selection/{id}")
	public String selection1(@PathVariable("id") Integer id, Model model) {
		
		Site site = siteRepo.getOne(id);
		String nomSite = site.getNom();
		System.out.println("//Nom du site: " + nomSite);
		//List<Secteur> secteurs = siteService.chercherSecteurs(id);
		List<Secteur> secteurs = site.getSecteurs();
		//List<List<Voie>> voies = siteService.chercherVoies(secteurs);
		//List<Voie> voies = secteurs.get(0).getVoies();
	//erreur!!!	List<List<Longueur>> longueurs = siteService.chercherLongueurs(voies);
		
		List<String> listeSecteurs = siteService.decomposerSecteur(id);
		System.out.println("Taille liste Secteurs: " + listeSecteurs.size());
		for (int i=0 ; i<listeSecteurs.size(); i++ ) {
			System.out.println("//Nom secteur, rang: " + i +":" + listeSecteurs.get(i));
			
		}
		List<List<String>> listeVoies =siteService.decomposerVoie(secteurs);
		System.out.println("//Taille liste Voies: " + listeVoies.size());
		List<List<String>> listeLongueurs =siteService.decomposerLongueur(voies);
		System.out.println("//Taille liste Longueurs: " + listeLongueurs.size());
		
		String test = new String();
		for (int i=0; i<listeLongueurs.size(); i++){
			test = listeLongueurs.get(i).get(i);
			System.out.println("//Nom de la voie, rang " + i +": " + test);
		}
		
		int taille = 0;
		int [] tailles = new int[listeLongueurs.size()]; // Tailles des listes internes à la liste des listes
		System.out.println("//Taille de la liste des liste de longueurs: " + listeLongueurs.size());
		for (int i=0; i<listeLongueurs.size(); i++) {  // Evaluation taille du tableau
		
		 tailles[i]= listeLongueurs.get(i).size();  // Mesure taille de la liste i de la liste des listes
		 System.out.println("//Taille de la liste interne, rang: "+ i +": "+ tailles[i]);
		 taille =  tailles[i] + taille;
		
		}
		System.out.println("//Taille colonne des longueurs: " + taille);
		String [] tabNomsLongueurs = new String[taille];  // élements de la colonne des longueurs: noms des longueurs à faire apparaitre dans la table
		List<String> uneListeDansListeLongueurs = new ArrayList<String>();// une liste des élements de la colonne des longueurs
		int k=0;
		for (int i=0; i<listeLongueurs.size()-1; i++) {  // 
			 
			 uneListeDansListeLongueurs = listeLongueurs.get(i);  // Récupération de la liste i dans la liste de listes
			 for (int j=0; j<uneListeDansListeLongueurs.size()-1; j++) {
				 
				 tabNomsLongueurs[k] = uneListeDansListeLongueurs.get(j);
				 k++;
			 }
			
			}
		
		for (int i=0; i<taille-1; i++) {  // Test : affichage colonne des longueurs 
			
			System.out.println("//Noms des longueurs:" + tabNomsLongueurs[i]);
		}
		
		
		GroupeSite groupeSite = new GroupeSite(nomSite, listeSecteurs, listeVoies, listeLongueurs);
		model.addAttribute("site",groupeSite);
		
		return "selection";
	}// */
	
	@GetMapping("/selection")
	public String selection() {
		
		return "selection";
		
	}
	
	@GetMapping("/selection4")
	public String resume(Model model) {
		
		Site site = siteRepo.getOne(1);
		model.addAttribute("site", site);
		
		return "selection4";
	}
	
	
	@GetMapping("/choisirsite")
	public String choisirSite(Model model) {
		
		List<Site> sites = siteRepo.findAll();
		model.addAttribute("sites", sites);
		String nomSite = new String();
		model.addAttribute("nomSite", nomSite);
		return "choisirsite";
	}
	
	@PostMapping("/ok")
	public String choixsite(String nomSite) {
		
		System.out.println("Site choisi: " + nomSite);
		
		return "ok";
	}
	
	@PostMapping("/choisirsite")
	public String choixSite(String nomSite, Model model) {
	
	Site site = siteRepo.findByNom(nomSite);
	model.addAttribute("site", site);
	
	return "selection4";
		
		
		
		
	}
	
}