package ru.defimov.showcase.appmap.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.defimov.showcase.appmap.domain.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
