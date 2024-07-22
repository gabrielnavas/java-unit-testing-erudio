package io.github.gabrielnavas.rest_with_springboot.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Given Person Object When ave then return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {
        Person person = new Person("John", "Carry", "1 Street", "Male");

        person = personRepository.save(person);

        assertNotNull(person);
        assertNotNull(person.getId());
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
        assertNotNull(person.getAddress());
        assertNotNull(person.getGender());

        assertTrue(person.getId() >= 1L);

        assertEquals("John", person.getFirstName());
        assertEquals("Carry", person.getLastName());
        assertEquals("1 Street", person.getAddress());
        assertEquals("Male", person.getGender());
    }
}
