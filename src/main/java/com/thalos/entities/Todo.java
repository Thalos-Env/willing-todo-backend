package com.thalos.entities;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "tb_todo")
@RequiredArgsConstructor
@Data
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private OffsetDateTime targetDate;
	private String username;
	private boolean done;
}
