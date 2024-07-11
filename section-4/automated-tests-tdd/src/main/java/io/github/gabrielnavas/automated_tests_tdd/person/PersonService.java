package io.github.gabrielnavas.automated_tests_tdd.person;

import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {
        var id = new AtomicLong().incrementAndGet();
        return new Person(
                id,
                person.getFirstname(),
                person.getLastname(),
                person.getEmail(),
                person.getStreet(),
                person.getGender()
        );
    }
}
