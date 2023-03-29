package com.thalos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thalos.entities.Todo;
import com.thalos.entities.User;
import com.thalos.services.TodoService;
import com.thalos.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	private final UserService userService;
	
	public User verifyProfile(String username) {
		return userService.getUserByUsername(username);
	}

	@PostMapping("/{username}/todos")
	public ResponseEntity<Todo> getTodoByUsername(@PathVariable String username, @RequestBody Todo todo) {
		this.verifyProfile(username);
		
		Todo result = todoService.createTodo(todo);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping("/{username}/todos/{id}")
	public ResponseEntity<Todo> modifyTodoById(@PathVariable String username, @RequestBody Todo todo, @PathVariable Long id) {
		this.verifyProfile(username);
		
		Todo result = todoService.modifyTodoById(todo, id);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{username}/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable String username, @PathVariable Long id) {
		this.verifyProfile(username);
		
		Todo result = todoService.getTodoById(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/{username}/todos")
	public ResponseEntity<List<Todo>> getTodos(@PathVariable String username) {
		this.verifyProfile(username);
		
		List<Todo> result = todoService.getTodos(username);

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{username}/todos/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable String username, @PathVariable Long id) {
		this.verifyProfile(username);
		
		todoService.deleteTodoById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
