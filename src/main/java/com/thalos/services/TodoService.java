package com.thalos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thalos.entities.Todo;
import com.thalos.repositories.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final TodoRepository todoRepository;

	public Todo createTodo(Todo todo) {		
		todoRepository.save(todo);

		return todo;
	}

	public Todo modifyTodoById(Todo newTodo, Long id) {
		Todo oldTodo = this.getTodoById(id);
		
		oldTodo.setDescription(newTodo.getDescription());
		oldTodo.setTargetDate(newTodo.getTargetDate());
		oldTodo.setDone(newTodo.isDone());
		oldTodo.setUsername(newTodo.getUsername());
		
		todoRepository.save(oldTodo);

		return oldTodo;
	}

	public Todo getTodoById(Long id) {
		Todo todo = todoRepository.findById(id).get();

		return todo;
	}
	
	public List<Todo> getTodos(String username) {
		List<Todo> todo = todoRepository.findAllByUsername(username);

		return todo;
	}
	
	public void deleteTodoById(Long id) {
		todoRepository.deleteById(id);
	}
}
