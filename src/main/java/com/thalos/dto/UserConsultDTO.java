package com.thalos.dto;

import lombok.Data;

@Data
public class UserConsultDTO {
	
	private Long id;
	private String username;
	private String password;
	private String profile;

	public UserConsultDTO(Long id, String username, String password, String profile) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.profile = profile;
	}
}
