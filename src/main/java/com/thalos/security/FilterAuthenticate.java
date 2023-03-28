package com.thalos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.thalos.entities.User;
import com.thalos.services.AuthenticateService;
import com.thalos.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FilterAuthenticate extends OncePerRequestFilter {
	
	private final AuthenticateService authenticateService;
	private final UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String token = null;
		if(header != null && header.startsWith("Bearer")) {
			token = header.substring(7, header.length());
		}
		
		if(authenticateService.verificarToken(token)) {
			Long idUsuario = authenticateService.retornarIdUsuario(token);
			User usuario = userService.getUserById(idUsuario);												
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
		}
		
		filterChain.doFilter(request, response);
	}
}

