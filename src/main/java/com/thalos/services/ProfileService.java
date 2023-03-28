package com.thalos.services;

import org.springframework.stereotype.Service;

import com.thalos.entities.Profile;
import com.thalos.repositories.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
	
	private final ProfileRepository profileRepository;

	public Profile searchProfileById(Long id) {
		return profileRepository.findById(id).get();
	}
}
