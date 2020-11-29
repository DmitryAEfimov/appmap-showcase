package ru.defimov.showcase.appmap.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.defimov.showcase.appmap.domain.Person;
import ru.defimov.showcase.appmap.service.PersonService;

@RestController
@RequestMapping( path = "/api/person", consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE )
public class PersonRestController {

	@Autowired
	private PersonService service;

	@GetMapping( value = "/{id}" )
	public PersonDto getById(@PathVariable( "id" ) long id) {
		Person person = service.findById(id);
		return asDto(person);
	}

	@PostMapping( value = "" )
	public ResponseEntity<PersonDto> save(@RequestBody Person person) {
		Person saved = service.save(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(asDto(saved));
	}

	private PersonDto asDto(Person person) {
		return new PersonDto.PersonDtoBuilder().id(person.getId()).name(person.getName()).build();
	}
}
