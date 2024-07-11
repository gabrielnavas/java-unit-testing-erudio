package io.github.gabrielnavas.automated_tests_tdd.person;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String street;
    private final String gender;

    public Person() {
        this(0L, "", "", "", "", "");
    }

    public Person(Long id, String firstname, String lastname, String email, String street, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.street = street;
        this.gender = gender;
    }

    public Person(String firstname, String lastname, String email, String street, String gender) {
        this(0L, firstname, lastname, email, street, gender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstname, person.firstname) && Objects.equals(lastname, person.lastname) && Objects.equals(email, person.email) && Objects.equals(street, person.street) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, email, street, gender);
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getGender() {
        return gender;
    }
}
