package com.thalos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.dto.UserConsultDTO;
import com.thalos.dto.UserMapper;
import com.thalos.dto.UserRegisterDTO;
import com.thalos.entities.Profile;
import com.thalos.entities.User;
import com.thalos.services.ProfileService;
import com.thalos.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final ProfileService profileService;

	@PostMapping
	public ResponseEntity<UserConsultDTO> createUser(@RequestBody UserRegisterDTO userDTO) {
		Profile verifyProfile = profileService.searchProfileById(userDTO.getProfileId());
		
		User newUser = UserMapper.fromDTO(userDTO, verifyProfile);
		User result = userService.createUser(newUser);
		
		UserConsultDTO consult = UserMapper.fromEntity(result);
	
		return new ResponseEntity<>(consult, HttpStatus.CREATED);
	}
}
