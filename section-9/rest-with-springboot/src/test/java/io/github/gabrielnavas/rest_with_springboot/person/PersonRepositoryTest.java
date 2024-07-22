package io.github.gabrielnavas.rest_with_springboot.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Given Person Object When save then return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {
        // Given
        Person person = new Person("John", "Carry", "john@email.com", "1 Street", "Male");

        // When
        person = personRepository.save(person);

        // Then
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
        // Given
        Person person1 = new Person("John", "Carry", "john@email.com", "1 Street", "Male");
        Person person2 = new Person("Mary", "Luth", "mary@email.com", "10 Street", "Woman");

        String lastNameSubstring = "rry";
        PageRequest page = PageRequest.of(0, 10);
        int expectedTotalElements = 1;

        personRepository.save(person1);
        personRepository.save(person2);

        // When
        Page<Person> person = personRepository.findAllByQuery(lastNameSubstring, page);

        // Then
        assertNotNull(person);
        assertEquals(expectedTotalElements, person.getTotalElements());
    }

    @DisplayName("Given Person Object When Find By Id then return Person Object")
    @Test
    void testGivenPersonObject_WhenFindById_thenReturnPersonObject() {
        // Given
        Person person = new Person("John", "Carry", "john@email.com", "1 Street", "Male");
        Long expectedPersonId = 1L;
        person = personRepository.save(person);

        // When
        Optional<Person> actualPersonOptional = personRepository.findById(person.getId());
        person = actualPersonOptional.get();

        // Then
        assertNotNull(person);
        assertNotNull(person.getFirstName());
        assertNotNull(person.getLastName());
        assertNotNull(person.getAddress());
        assertNotNull(person.getGender());

        assertTrue(person.getId() >= expectedPersonId);

        assertEquals("John", person.getFirstName());
        assertEquals("Carry", person.getLastName());
        assertEquals("1 Street", person.getAddress());
        assertEquals("Male", person.getGender());
    }

    @DisplayName("Given Person Object When Find By Email then return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByEmail_ThenReturnPersonObject() {
        // Given
        Person person = new Person("John", "Carry", "john@email.com", "1 Street", "Male");
        Long expectedPersonId = 1L;
        personRepository.save(person);

        // When
        Optional<Person> optionalPerson = personRepository.findByEmail(person.getEmail());
        Person receivedPerson = optionalPerson.get();

        // Then
        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());

        assertTrue(receivedPerson.getId() >= expectedPersonId);

        assertEquals("John", person.getFirstName());
        assertEquals("Carry", person.getLastName());
        assertEquals("1 Street", person.getAddress());
        assertEquals("Male", person.getGender());
    }

    @DisplayName("Given Person Object When Update Person Then Return Updated Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {
        // Given
        Person person = new Person("John", "Carry", "john@email.com", "1 Street", "Male");
        Long expectedPersonId = 1L;
        person = personRepository.save(person);

        // When
        person.setAddress("Any Other Street");
        person.setFirstName("Any Other First Name");
        person.setLastName("Any Other Last Name");
        person.setEmail("Any Other Email");
        person.setGender("Any Other Gender");
        personRepository.save(person);

        // Then
        Person personUpdated = personRepository.findById(person.getId()).get();

        assertNotNull(personUpdated);
        assertNotNull(personUpdated.getFirstName());
        assertNotNull(personUpdated.getLastName());
        assertNotNull(personUpdated.getAddress());
        assertNotNull(personUpdated.getGender());

        assertTrue(personUpdated.getId() >= expectedPersonId);

        assertEquals(person.getFirstName(), personUpdated.getFirstName());
        assertEquals(person.getLastName(), personUpdated.getLastName());
        assertEquals(person.getAddress(), personUpdated.getAddress());
        assertEquals(person.getGender(), personUpdated.getGender());
    }

    @DisplayName("Given Person Object When Delete Person Then Remove Person Object")
    @Test
    void testGivenPersonObject_WhenDeletePerson_thenRemovePersonObject() {
        // Given
        Person person = new Person("John", "Carry", "john@email.com", "1 Street", "Male");
        person = personRepository.save(person);

        // When
        personRepository.delete(person);

        // Then
        Optional<Person> optionalPerson = personRepository.findById(person.getId());

        assertTrue(optionalPerson.isEmpty());
    }
}
