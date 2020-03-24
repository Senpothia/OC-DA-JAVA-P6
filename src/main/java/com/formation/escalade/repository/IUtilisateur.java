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
	
	@Query(value = "SELECT * FROM utilisateur u WHERE u.membre = TRUE  AND u.actif = TRUE", nativeQuery = true)
	List<Utilisateur> findAllMembresActifsNative();

}
