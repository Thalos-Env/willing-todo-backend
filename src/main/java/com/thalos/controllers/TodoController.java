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
import com.thalos.services.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;

	@PostMapping
	public ResponseEntity<Todo> getTodoById(@RequestBody Todo todo) {
		Todo result = todoService.createTodo(todo);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Todo> modifyTodoById(@RequestBody Todo todo, @PathVariable Long id) {
		Todo result = todoService.modifyTodoById(todo, id);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		Todo result = todoService.getTodoById(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Todo>> getTodos() {
		List<Todo> result = todoService.getTodos();

		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
		todoService.deleteTodoById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
