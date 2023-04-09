package com.thalos.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.thalos.builder.TodoBuilder;
import com.thalos.entities.Todo;
import com.thalos.repositories.TodoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class TodoServiceTest {
	
	@MockBean
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoService todoService;
	
	private TodoBuilder todoBuilder;
	private Long id;
	private String username;
	
	@BeforeEach
	public void setup() {
		id = (long) 100;
		username = "Test";
		setupTodoBuilder();
	}
	
	public void setupTodoBuilder() {
		todoBuilder = new TodoBuilder().withId(id).withDescription("testing").withTargetDate(OffsetDateTime.now()).withUsername(username).withDone(true);
	}

	@Test
	@DisplayName("Should find todo by id")
	public void shouldFindTodoById() {
		when(todoRepository.findById(id)).thenReturn(Optional.of(todoBuilder));
		
		Todo result = todoService.getTodoById(id);

		assertEquals(todoBuilder, result);
	}
	
	@Test
	@DisplayName("Should all user todos")
	public void shouldAllUserTodos() {
		when(todoRepository.findAllByUsername(username)).thenReturn(List.of(todoBuilder));
		
		List<Todo> result = todoService.getTodos(username);

		assertEquals(List.of(todoBuilder), result);
	}
}
