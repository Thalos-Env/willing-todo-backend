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

	public Todo(Long id, String description, OffsetDateTime targetDate, String username, boolean done) {
		this.id = id;
		this.description = description;
		this.targetDate = targetDate;
		this.username = username;
		this.done = done;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String description;
	protected OffsetDateTime targetDate;
	protected String username;
	protected boolean done;
}
