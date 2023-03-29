package com.thalos.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.dto.auth.AuthenticateDTO;
import com.thalos.dto.auth.TokenDTO;
import com.thalos.services.AuthenticateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticateController {

	private final AuthenticateService authenticateService;

	public Authentication verifyUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AuthenticateDTO authForm) {
		return ResponseEntity.ok(authenticateService.autenticar(authForm));
	}

	@GetMapping("/refresh")
	public ResponseEntity<TokenDTO> refreshToken() {
		Authentication userOn = verifyUser();
		TokenDTO tokenDTO = new TokenDTO(authenticateService.gerarToken(userOn));

		return ResponseEntity.ok(tokenDTO);
	}
}