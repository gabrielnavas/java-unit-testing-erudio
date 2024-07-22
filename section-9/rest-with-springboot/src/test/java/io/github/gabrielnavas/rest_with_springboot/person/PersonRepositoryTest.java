package io.github.gabrielnavas.rest_with_springboot.person;

import org.junit.jupiter.api.BeforeEach;
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

    protected Person person1;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setup() {
        // Given
        person1 = new Person("John", "Carry", "john@email.com", "1 Street", "Male");
    }

    @DisplayName("Given Person Object When save then return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {
        // When
        person1 = personRepository.save(person1);

        // Then
        assertNotNull(person1);
        assertNotNull(person1.getId());
        assertNotNull(person1.getFirstName());
        assertNotNull(person1.getLastName());
        assertNotNull(person1.getAddress());
        assertNotNull(person1.getGender());

        assertTrue(person1.getId() >= 1L);

        assertEquals("John", person1.getFirstName());
        assertEquals("Carry", person1.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Objects When Find All By Query then return List Person")
    @Test
    void testGivenPersonObjects_WhenFindAllByQuery_thenReturnListPerson() {
        // Given
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
        Long expectedPersonId = 1L;
        person1 = personRepository.save(person1);

        // When
        Optional<Person> actualPersonOptional = personRepository.findById(person1.getId());
        person1 = actualPersonOptional.get();

        // Then
        assertNotNull(person1);
        assertNotNull(person1.getFirstName());
        assertNotNull(person1.getLastName());
        assertNotNull(person1.getAddress());
        assertNotNull(person1.getGender());

        assertTrue(person1.getId() >= expectedPersonId);

        assertEquals("John", person1.getFirstName());
        assertEquals("Carry", person1.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Find By Email then return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByEmail_ThenReturnPersonObject() {
        // Given
        Long expectedPersonId = 1L;
        personRepository.save(person1);

        // When
        Optional<Person> optionalPerson = personRepository.findByEmail(person1.getEmail());
        Person receivedPerson = optionalPerson.get();

        // Then
        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());

        assertTrue(receivedPerson.getId() >= expectedPersonId);

        assertEquals("John", person1.getFirstName());
        assertEquals("Carry", person1.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Find By Firstname and LastName then return Person Object With Basic Parameters")
    @Test
    void testGivenPersonObject_WhenFindByFirstNameAndLastEmail_ThenReturnPersonObject_WithBasicParameters() {
        // Given
        String firstName = "John";
        String lastName = "Carry";
        Long expectedPersonId = 1L;
        personRepository.save(person1);

        // When
        Optional<Person> optionalPerson = personRepository.findByFirstNameAndLastNameBasic(firstName, lastName);

        // Then
        assertTrue(optionalPerson.isPresent());
        Person receivedPerson = optionalPerson.get();

        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());
        assertTrue(receivedPerson.getId() >= expectedPersonId);
        assertEquals(firstName, receivedPerson.getFirstName());
        assertEquals(lastName, receivedPerson.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Find By Firstname and LastName then return Person Object Param Named")
    @Test
    void testGivenPersonObject_WhenFindByFirstNameAndLastEmail_ThenReturnPersonObject_Param_Named() {
        // Given
        String firstName = "John";
        String lastName = "Carry";
        Long expectedPersonId = 1L;
        personRepository.save(person1);

        // When
        Optional<Person> optionalPerson = personRepository.findByFirstNameAndLastNameParamNamed(firstName, lastName);

        // Then
        assertTrue(optionalPerson.isPresent());
        Person receivedPerson = optionalPerson.get();

        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());
        assertTrue(receivedPerson.getId() >= expectedPersonId);
        assertEquals(firstName, receivedPerson.getFirstName());
        assertEquals(lastName, receivedPerson.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Find By Firstname and LastName then return Person Object Native SQL")
    @Test
    void testGivenPersonObject_WhenFindByFirstNameAndLastEmail_ThenReturnPersonObject_Native_SQL() {
        // Given
        String firstName = "John";
        String lastName = "Carry";
        Long expectedPersonId = 1L;
        personRepository.save(person1);

        // When
        Optional<Person> optionalPerson = personRepository.findByFirstNameAndLastNameNativeSQL(firstName, lastName);

        // Then
        assertTrue(optionalPerson.isPresent());
        Person receivedPerson = optionalPerson.get();

        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());
        assertTrue(receivedPerson.getId() >= expectedPersonId);
        assertEquals(firstName, receivedPerson.getFirstName());
        assertEquals(lastName, receivedPerson.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Find By Firstname and LastName then return Person Object With Native SQL And Named Parameters")
    @Test
    void testGivenPersonObject_WhenFindByFirstNameAndLastEmail_ThenReturnPersonObject_WithNativeSQLAndNamedParameters() {
        // Given
        String firstName = "John";
        String lastName = "Carry";
        Long expectedPersonId = 1L;
        personRepository.save(person1);

        // When
        Optional<Person> optionalPerson = personRepository.findByFirstNameAndLastNameNativeSQLWithNamedParameters(firstName, lastName);

        // Then
        assertTrue(optionalPerson.isPresent());
        Person receivedPerson = optionalPerson.get();

        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());
        assertTrue(receivedPerson.getId() >= expectedPersonId);
        assertEquals(firstName, receivedPerson.getFirstName());
        assertEquals(lastName, receivedPerson.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Find By Firstname and LastName then return Person Object Index Parameters")
    @Test
    void testGivenPersonObject_WhenFindByFirstNameAndLastEmail_ThenReturnPersonObject_Index_Parameters() {
        // Given
        String firstName = "John";
        String lastName = "Carry";
        Long expectedPersonId = 1L;
        personRepository.save(person1);

        // When
        Optional<Person> optionalPerson = personRepository.findByFirstNameAndLastNameIndexParameters(firstName, lastName);

        // Then
        assertTrue(optionalPerson.isPresent());
        Person receivedPerson = optionalPerson.get();

        assertNotNull(receivedPerson);
        assertNotNull(receivedPerson.getFirstName());
        assertNotNull(receivedPerson.getLastName());
        assertNotNull(receivedPerson.getAddress());
        assertNotNull(receivedPerson.getGender());
        assertTrue(receivedPerson.getId() >= expectedPersonId);
        assertEquals(firstName, receivedPerson.getFirstName());
        assertEquals(lastName, receivedPerson.getLastName());
        assertEquals("1 Street", person1.getAddress());
        assertEquals("Male", person1.getGender());
    }

    @DisplayName("Given Person Object When Update Person Then Return Updated Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {
        // Given
        Long expectedPersonId = 1L;
        person1 = personRepository.save(person1);

        // When
        person1.setAddress("Any Other Street");
        person1.setFirstName("Any Other First Name");
        person1.setLastName("Any Other Last Name");
        person1.setEmail("Any Other Email");
        person1.setGender("Any Other Gender");
        personRepository.save(person1);

        // Then
        Person personUpdated = personRepository.findById(person1.getId()).get();

        assertNotNull(personUpdated);
        assertNotNull(personUpdated.getFirstName());
        assertNotNull(personUpdated.getLastName());
        assertNotNull(personUpdated.getAddress());
        assertNotNull(personUpdated.getGender());

        assertTrue(personUpdated.getId() >= expectedPersonId);

        assertEquals(person1.getFirstName(), personUpdated.getFirstName());
        assertEquals(person1.getLastName(), personUpdated.getLastName());
        assertEquals(person1.getAddress(), personUpdated.getAddress());
        assertEquals(person1.getGender(), personUpdated.getGender());
    }

    @DisplayName("Given Person Object When Delete Person Then Remove Person Object")
    @Test
    void testGivenPersonObject_WhenDeletePerson_thenRemovePersonObject() {
        // Given
        person1 = personRepository.save(person1);

        // When
        personRepository.delete(person1);

        // Then
        Optional<Person> optionalPerson = personRepository.findById(person1.getId());

        assertTrue(optionalPerson.isEmpty());
    }
}
