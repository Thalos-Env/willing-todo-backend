package com.thalos.dto.auth;

import lombok.Data;

@Data
public class AuthenticateDTO {
	private String username;
	private String password;
}
