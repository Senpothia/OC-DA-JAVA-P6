package com.formation.escalade.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.repository.IUtilisateur;
import com.sun.tools.javac.util.List;

@Service
public class UtilisateurService implements UserDetailsService {

	@Autowired
	private IUtilisateur utilisateurRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur user = utilisateurRepo.findByEmail(username);
		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return user.getEmail();
			}

			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return user.getPasse();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub

				return List.of(new GrantedAuthority() {

					@Override
					public String getAuthority() {
						// TODO Auto-generated method stub
						if (user.isMembre()) {
							return "MEMBRE";
						}
						return "USER";
					}
				}

				);

			}
		};
	}

}
