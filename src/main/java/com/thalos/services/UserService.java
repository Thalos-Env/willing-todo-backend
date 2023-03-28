package com.thalos.services;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thalos.entities.User;
import com.thalos.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Transactional 
	public User createUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	public User getUserById(Long id) {
		User user = userRepository.findById(id).get();

		return user;
	}

	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username).get();
		
		if(user == null)
			return null;
			
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = getUserByUsername(username);

		return userDetails;
	}
}
