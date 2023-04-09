package com.thalos.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thalos.builder.TodoBuilder;
import com.thalos.builder.UserBuilder;
import com.thalos.entities.Profile;
import com.thalos.services.TodoService;
import com.thalos.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TodoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoServiceMock;
	
	@MockBean
	private UserService userService;

	private static final String REQUEST_MAPPING = "/users/Test/todos";
	private ObjectMapper objectMapper;
	
	private TodoBuilder todoBuilder;
	private UserBuilder userBuilder;
	
	private Long id;
	private String username;

	@BeforeEach
	void setup() {
		id = (long) 100;
		username = "Test";
		setupTodoBuilder();

		objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	public void setupTodoBuilder() {
		todoBuilder = new TodoBuilder().withId(id).withDescription("testing").withTargetDate(OffsetDateTime.now()).withUsername(username).withDone(true);
	}
	
	public void setupUserBuilder() {
		userBuilder = new UserBuilder().withId(id).withUsername(username).withPassword("1234").withProfile(new Profile());
	}

	@Test
	@DisplayName("Should find todo by id and status 200")
	public void shouldFindTodoByIdAndStatus200() throws Exception {
		when(todoServiceMock.getTodoById(eq(id))).thenReturn(todoBuilder);
		when(userService.getUserByUsername(username)).thenReturn(userBuilder);
		
		String result = objectMapper.writeValueAsString(todoBuilder);
		
		mockMvc.perform(get(REQUEST_MAPPING + "/" + id)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk())
				  .andExpect(content().json(result));
	}	
	
	@Test
	@DisplayName("Should find all user todos and status 200")
	public void shouldFindAllUserTodosAndStatus200() throws Exception {
		when(todoServiceMock.getTodos(username)).thenReturn(List.of(todoBuilder));
		when(userService.getUserByUsername(username)).thenReturn(userBuilder);

		mockMvc.perform(get(REQUEST_MAPPING)
				  .contentType(MediaType.APPLICATION_JSON))
				  .andExpect(status().isOk())
				  .andExpect(jsonPath("$.size()").value(List.of(todoBuilder).size()));
	}
}
