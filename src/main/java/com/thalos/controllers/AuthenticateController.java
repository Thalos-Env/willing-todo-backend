package com.thalos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.dto.auth.AuthenticateDTO;
import com.thalos.dto.auth.TokenDTO;
import com.thalos.services.AuthenticateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticateController {

	private final AuthenticateService authenticateService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody AuthenticateDTO authForm) {
		return ResponseEntity.ok(authenticateService.autenticar(authForm));
	}
}