package com.thalos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalos.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
