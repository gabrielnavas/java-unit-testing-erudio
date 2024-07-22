package io.github.gabrielnavas.rest_with_springboot.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Given Person Object When save then return Saved Person")
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

    @DisplayName("Given Person Objects When Find All By Query then return List Person")
    @Test
    void testGivenPersonObjects_WhenFindAllByQuery_thenReturnListPerson() {
        Person person1 = new Person("John", "Carry", "1 Street", "Male");
        Person person2 = new Person("Mary", "Luth", "10 Street", "Woman");
        String lastNameSubstring = "rry";

        personRepository.save(person1);
        personRepository.save(person2);

        PageRequest page = PageRequest.of(0, 10);
        Page<Person> person = personRepository.findAllByQuery(lastNameSubstring, page);

        assertNotNull(person);
        assertEquals(1, person.getTotalElements());
    }
}
