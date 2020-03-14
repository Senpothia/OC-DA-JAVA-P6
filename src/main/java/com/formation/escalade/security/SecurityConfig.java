package com.formation.escalade.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.formation.escalade.model.Utilisateur;
import com.formation.escalade.service.UtilisateurService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private DataSource dataSource; 
	
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/images/**",
        		  	   "/css/**").permitAll()
          .antMatchers("/",
        		  	 	"/galerie/**",
        		  	 	"/presentation",
        		  	 	"/connexion",
        		  	 	"/viewsite/**",
        		  	 	"/structure/**",
        		  	 	"/topos/**",
        		  	 	"/commentaires/**",
        		  	 	"/compte").permitAll()
          .anyRequest().authenticated()
          .and().formLogin()
          .loginPage("/connexion")
          .defaultSuccessUrl("/espace")
          .failureUrl("/connexion?error=true").permitAll();
        
    }
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().
		dataSource(dataSource).
		usersByUsernameQuery("select email, passe, membre from utilisateur where email=?")
		.authoritiesByUsernameQuery(
		"select u.email, p.role from user_profil up " + 
		"inner join utilisateur u on u.id = up.id_user " + 
	    "inner join profil p on p.id = up.id_profil " + 
		"where u.email = ?");
		}
	*/
	@Bean
	public PasswordEncoder passwordEncoder () {
		
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationProvider authentificationProvider (UtilisateurService service, PasswordEncoder encoder) {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(service);
		auth.setPasswordEncoder(encoder);
		return auth;
	}
	
	
	
		
}
