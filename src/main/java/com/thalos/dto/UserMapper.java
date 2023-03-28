package com.thalos.dto;

import com.thalos.entities.Profile;
import com.thalos.entities.User;

public class UserMapper {

	public static User fromDTO(UserRegisterDTO dto, Profile profile) {		
		return new User(null, dto.getUsername(), dto.getPassword(), profile);
	}
	
	public static UserConsultDTO fromEntity(User user) {
		return new UserConsultDTO(user.getId(), user.getUsername(), user.getPassword(), user.getProfile().getName());
	}
}
