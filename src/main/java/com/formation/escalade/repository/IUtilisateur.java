package com.formation.escalade.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.formation.escalade.model.Utilisateur;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface IUtilisateur extends JpaRepository<Utilisateur, Integer> {

	Utilisateur findById(int id);

	Utilisateur findByEmail(String email);

	Utilisateur findByNomAndPrenom(String nom, String prenom);

	@Query("SELECT u FROM Utilisateur u WHERE u.membre = TRUE")
	// Collection<Utilisateur> findAllActiveUsers();
	Collection<Utilisateur> findAllMembres();

	@Query("SELECT u FROM Utilisateur u WHERE u.membre = FALSE")
	// Collection<Utilisateur> findAllInactiveUsers();
	Collection<Utilisateur> findAllUtilisateurs();

	@Query(value = "SELECT * FROM utilisateur u WHERE u.membre = TRUE", nativeQuery = true)
	Collection<Utilisateur> findAllMembresNative();

	Utilisateur findByNomOrPrenom(String phrase, String phrase2);
	
	List<Utilisateur> findByNomOrPrenomIgnoreCase(String phrase, String phrase2);

	@Query(value = "SELECT * FROM utilisateur u WHERE u.membre = TRUE  AND u.actif = TRUE", nativeQuery = true)
	List<Utilisateur> findAllMembresActifsNative();

	//List<Utilisateur> findByNomLike(String string);

	List<Utilisateur> findByNomIgnoreCase(String string);

	@Query(value = "select * from utilisateur u where u.nom like 'L%'", nativeQuery = true)
	List<Utilisateur> findAllRs();

	@Query(value = "SELECT * FROM utilisateur WHERE email = ?1", nativeQuery = true)
	Utilisateur findByEmailAddress(String emailAddress);

	@Query(value = "select * from utilisateur u where u.nom like ?1", nativeQuery = true)
	List<Utilisateur> findAllStartBy(String string);

	@Query(value = "select * from utilisateur u where u.nom like ?1 or u.prenom like ?1", nativeQuery = true)
	List<Utilisateur> findAllUserStartBy(String string);

}
