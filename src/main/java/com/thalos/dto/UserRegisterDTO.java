package com.thalos.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {

	private String username;
	private String password;
	private Long profileId;
}
