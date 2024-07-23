package io.github.gabrielnavas.rest_with_springboot.person;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String attribute, String value) {
        super(String.format("person not found with %s %s", attribute, value));
    }
}
