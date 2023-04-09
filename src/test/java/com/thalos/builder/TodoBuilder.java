package com.thalos.builder;

import java.time.OffsetDateTime;

import com.thalos.entities.Todo;

public class TodoBuilder extends Todo {

	public Todo build() {
		return new Todo(id, description, targetDate, username, done);
	}
	
	public TodoBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public TodoBuilder withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public TodoBuilder withTargetDate(OffsetDateTime targetDate) {
		this.targetDate = targetDate;
		return this;
	}
	
	public TodoBuilder withUsername(String username) {
		this.username = username;
		return this;
	}
	
	public TodoBuilder withDone(boolean done) {
		this.done = done;
		return this;
	}
}
