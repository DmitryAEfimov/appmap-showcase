package ru.defimov.showcase.appmap.repository;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.defimov.showcase.appmap.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName( "Person Repository Test" )
@SpringBootTest
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repository;
	private Person person;

	@BeforeEach
	public void setup() {
		person = person("Ivan");
	}

	@Test
	@DisplayName( "Save entity properly" )
	void shouldSaveEntityTest() {
		val newPerson = repository.save(person);
		assertThat(newPerson.getId()).isNotNull();
	}

	@Test
	@DisplayName( "Find entity properly" )
	void shouldFindById() {
		val newPerson = repository.save(person);
		val foundById = repository.findById(newPerson.getId());
		Assertions.assertThat(foundById.get())
		          .isNotNull().isEqualToComparingFieldByField(newPerson);
	}

	private Person person(String name) {
		return new Person(name);
	}
}
