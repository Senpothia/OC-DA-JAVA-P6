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
        		  	   "/css/**"
        		  	   ).permitAll()
          .antMatchers("/",
        		  	 	"/galerie/**",
        		  	 	"/presentation",
        		  	 	"/connexion",
        		  	 	"/viewsite/**",
        		  	 	"/structure/**",
        		  	 	"/topos/**",
        		  	 	"/commentaires/**",
        		  	 	"/compte",
        		  	 	"/informations/**",
        		  	 	"/rechercher",
        		  	 	"/user",
        		  	 	"/recherche/**").permitAll()
          .antMatchers("/administration2").hasAuthority("MEMBRE")
          .anyRequest().authenticated()
          .and().formLogin()
          .loginPage("/connexion")
          .defaultSuccessUrl("/espace")
          .failureUrl("/connexion?error=true").permitAll();
        
    }
	
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
