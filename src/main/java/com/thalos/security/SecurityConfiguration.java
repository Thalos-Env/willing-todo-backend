package com.thalos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.thalos.services.AuthenticateService;
import com.thalos.services.UserService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)	
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserService userService;
	private final AuthenticateService authenticateService;
	
	@Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;
	
	public SecurityConfiguration(@Lazy UserService userService, @Lazy AuthenticateService authenticateService) {
		this.userService = userService;
		this.authenticateService = authenticateService;
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
		    .antMatchers(HttpMethod.POST, "/authenticate", "/api-docs/**", "/swagger-ui.html**").permitAll()
		    .antMatchers("/users/**").authenticated()	
		    .antMatchers("/authenticate/**").authenticated()
		    .antMatchers("/refresh**").authenticated()
		    .and().csrf().disable()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and().addFilterBefore(new FilterAuthenticate(authenticateService, userService), UsernamePasswordAuthenticationFilter.class)
		    .exceptionHandling()
            .authenticationEntryPoint(unauthorizedEntryPoint);
	}
}
