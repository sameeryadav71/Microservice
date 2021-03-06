package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
		.allowedMethods("GET", "POST", "DELETE","PUT").allowedOrigins("http://localhost:4200");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.cors().and().httpBasic().and().authorizeRequests()
//				// allowing this urls without authentication
//				.antMatchers("/account/**", "/account/login", "/logout", "not-found").permitAll()
//				// only admin can access
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				// user and admin both can access
//				.antMatchers("/user/**").hasAnyRole("ADMIN", "USER").antMatchers("/").permitAll().and().formLogin();
		
		
		http.cors().and()
		// starts authorizing configurations
		.authorizeRequests()
		// ignoring the guest's urls "
		.antMatchers("/account/register","/account/login","/logout","not-found").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//		// user and admin both can access/
//		.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
		// authenticate all remaining URLS
		.anyRequest().fullyAuthenticated().and()
      /* "/logout" will log the user out by invalidating the HTTP Session,
       * cleaning up any {link rememberMe()} authentication that was configured, */
		.logout()
        .permitAll()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
        .and()
		// enabling the basic authentication
		.httpBasic().and()
		// configuring the session on the server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
		// disabling the CSRF - Cross Site Request Forgery
		.csrf().disable();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		// or any domain that you want to restrict to
//		configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	// @Override
	// public void addCorsMappings(CorsRegistry corsRegistry) {
	// corsRegistry
	// .addMapping("/**")
	// .allowedOrigins("http://localhost:4200")
	// .allowedMethods("*");
	// //.maxAge(3600L)
	// //.allowedHeaders("*")
	// //.exposedHeaders("Authorization")
	// //.allowCredentials(true);
	// }
}