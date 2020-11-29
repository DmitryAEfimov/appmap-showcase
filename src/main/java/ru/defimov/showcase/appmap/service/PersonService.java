package ru.defimov.showcase.appmap.service;

import ru.defimov.showcase.appmap.domain.Person;

public interface PersonService {

    Person save(Person person);

    Person findById(long id);
}
