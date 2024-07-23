package io.github.gabrielnavas.rest_with_springboot.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    protected Person person1;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setup() {
        // Given
        person1 = new Person(1L, "John", "Carry", "john@email.com", "1 Street", "Male");
    }

    @DisplayName("Given Person Object When Save Person Then Return Person Object")
    @Test
    public void testGivenPersonObject_WhenSavePerson_ThenReturnPersonObject() {
        // Given
        PersonRequest personRequest = new PersonRequest(person1.getFirstName(), person1.getLastName(), person1.getEmail(), person1.getAddress(), person1.getGender());
        when(personRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(personRepository.save(any())).thenReturn(person1);

        // When
        Person personCreated = personService.createPerson(personRequest);

        // Then
        assertNotNull(personCreated);
        assertTrue(personCreated.getId() >= 1L);
    }

    @DisplayName("Given Existing Email When Save Person Then Throws Exception")
    @Test
    public void testGivenExistingEmail_WhenSavePerson_ThenThrowsException() {
        // Given
        PersonRequest personRequest = new PersonRequest(person1.getFirstName(), person1.getLastName(), person1.getEmail(), person1.getAddress(), person1.getGender());
        when(personRepository.findByEmail(anyString())).thenReturn(Optional.of(person1));

        // When & Then
        PersonNotFoundException received = assertThrows(
                PersonNotFoundException.class,
                () -> personService.createPerson(personRequest),
                () -> "expected exception with class " + PersonNotFoundException.class.getName()
        );

        // Then
        verify(personRepository, never()).save(any(Person.class));
        assertEquals("person not found with email " + personRequest.getEmail(), received.getMessage());
    }
}
