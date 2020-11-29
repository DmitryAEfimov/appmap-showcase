package ru.defimov.showcase.appmap.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.defimov.showcase.appmap.domain.Person;
import ru.defimov.showcase.appmap.repository.PersonRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@DisplayName("Person Service Impl Test")
@SpringBootTest
class PersonServiceImplTest {

	@MockBean
	private PersonRepository repository;
	@Autowired
	private PersonService personService;

	@Test
	@DisplayName("Call repository save")
	void save() {
		personService.save(person());

		verify(repository).save(any());
	}

	@Test
	@DisplayName("Call repository find")
	void findById() {
		personService.findById(11);

		verify(repository).findById(anyLong());
	}

	private Person person() {
		return new Person("Ivan");
	}
}
