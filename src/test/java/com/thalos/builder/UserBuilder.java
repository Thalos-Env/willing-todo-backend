package com.thalos.builder;

import com.thalos.entities.Profile;
import com.thalos.entities.User;

public class UserBuilder extends User {
	private static final long serialVersionUID = 1L;

	public User build() {
		return new User(id, username, password, profile);
	}
	
	public UserBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder withUsername(String username) {
		this.username = username;
		return this;
	}
	
	public UserBuilder withProfile(Profile profile) {
		this.profile = profile;
		return this;
	}
}
