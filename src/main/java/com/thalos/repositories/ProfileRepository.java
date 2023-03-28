package com.thalos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalos.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
