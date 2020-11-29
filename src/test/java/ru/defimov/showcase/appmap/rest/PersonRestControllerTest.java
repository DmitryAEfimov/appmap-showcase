package ru.defimov.showcase.appmap.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.defimov.showcase.appmap.domain.Person;
import ru.defimov.showcase.appmap.service.PersonService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName( "REST Controller Test" )
@WebMvcTest( PersonRestController.class )
class PersonRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonService service;
	@Mock
	private Person person;

	private long personId = 42;
	private String personName = "Ivan";

	@BeforeEach
	public void setup() {
		when(person.getId()).thenReturn(personId);
		when(person.getName()).thenReturn(personName);
	}

	@Test
	@DisplayName( "Return GET /api/person/{id}" )
	void shouldResponsePersonDto() throws Exception {
		when(service.findById(anyLong()))
				.thenReturn(person);

		mvc.perform(get("/api/person/" + personId).contentType(APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(APPLICATION_JSON))
		   .andExpect(jsonPath("$.id").value(personId))
		   .andExpect(jsonPath("$.name").value(personName));
	}

	@Test
	@DisplayName( "Do POST /api/person" )
	void shouldResponsePersonDtoWithId() throws Exception {
		when(service.save(any()))
				.thenReturn(person);

		mvc.perform(
				post("/api/person").contentType(APPLICATION_JSON)
				                   .content("{\"name\": \"" + personName + "\"}")
				                   .contentType(APPLICATION_JSON)
		)
		   .andExpect(status().isCreated())
		   .andExpect(content().contentType(APPLICATION_JSON))
		   .andExpect(jsonPath("$.id").value(personId))
		   .andExpect(jsonPath("$.name").value(personName));
	}
}
