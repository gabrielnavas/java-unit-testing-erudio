package io.github.gabrielnavas.automated_tests_tdd.person;

public class PersonService implements IPersonService {
    @Override
    public Person createPerson(Person person) {
        return new Person();
    }
}
