package com.antoniosgarbi.security;

import java.util.Arrays;

import com.antoniosgarbi.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final Environment environment;
	private final UserDetailsServiceImpl userDetailsService;
	private final Argon2PasswordEncoder passwordEncoder;
	private final SecurityEnvironment securityEnvironment;

	public SecurityConfig(
			Environment environment, UserDetailsServiceImpl userDetailsService,
			Argon2PasswordEncoder passwordEncoder, SecurityEnvironment securityEnvironment) {
		this.environment = environment;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.securityEnvironment = securityEnvironment;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors().and().csrf().disable()
		.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers("/user/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST).hasRole("USER")
				.antMatchers(HttpMethod.PUT).hasRole("USER")
				.antMatchers(HttpMethod.DELETE).hasRole("USER")
				.anyRequest().authenticated().and().httpBasic()
		.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), securityEnvironment))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService, securityEnvironment))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}
