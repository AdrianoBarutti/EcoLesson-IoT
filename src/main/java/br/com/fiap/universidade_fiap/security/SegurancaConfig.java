package br.com.fiap.universidade_fiap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SegurancaConfig {
	
	@Bean
	public SecurityFilterChain filtrar(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests((request) -> 
	                request
	                .requestMatchers("/img/**", "/actuator/**", "/h2-console/**").permitAll()
	                // suas outras regras
	                .anyRequest().authenticated())
	        .formLogin((login) -> login
	            .loginPage("/login")
	            .defaultSuccessUrl("/", true)
	            .failureUrl("/login?falha=true")
	            .permitAll())
	        .logout((logout) -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout=true")
	            .permitAll())
	        .exceptionHandling((exception) -> exception.accessDeniedHandler(
	            (request, response, accessDeniedException) -> response.sendRedirect("/acesso_negado")))
	        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // << novo
	        .headers().frameOptions().disable(); // liberar frames para o H2 console

	    return http.build();
	}

	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	    return configuration.getAuthenticationManager();
	}
	
}
