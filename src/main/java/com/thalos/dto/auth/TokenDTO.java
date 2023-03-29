package com.thalos.dto.auth;

import lombok.Data;

@Data
public class TokenDTO {

	private String token;

	public TokenDTO() {}

	public TokenDTO(String token) {
		this.token = token;
	}
}