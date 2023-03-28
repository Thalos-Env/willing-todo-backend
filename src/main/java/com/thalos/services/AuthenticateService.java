package com.thalos.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thalos.dto.auth.AuthenticateDTO;
import com.thalos.dto.auth.TokenDTO;
import com.thalos.entities.User;
import com.thalos.exceptions.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
	@Value("${loja.jwt.expiration}")
	private String expiration;

	@Value("${loja.jwt.secret}")
	private String secret;

	@Value("${loja.jwt.issuer}")
	private String issuer;

	private final AuthenticationManager authManager;

	public TokenDTO autenticar(AuthenticateDTO authForm) throws AuthenticationException {
		try {
			Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getUsername(), authForm.getPassword()));			
			
			String token = gerarToken(authentication);

			return new TokenDTO(token);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
	}

	private Algorithm criarAlgoritmo() {
		return Algorithm.HMAC256(secret);
	}

	private String gerarToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();

		Date dataDeHoje = new Date();
		Date dataDeExpiracao = new Date(dataDeHoje.getTime() + Long.parseLong(expiration));
		return JWT.create()
				  .withIssuer(issuer)
				  .withExpiresAt(dataDeExpiracao)
				  .withSubject(
						  principal.getId()
						  .toString())
				  .sign(this.criarAlgoritmo());
	}

	public boolean verificarToken(String token) {
		try {
			if (token == null) {
				return false;
			}

			JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
			return true;
		} catch (JWTVerificationException exception) {
			return false;
		}
	}

	public Long retornarIdUsuario(String token) {
		String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();
		return Long.parseLong(subject);
	}
}

