package io.github.gabrielnavas.rest_with_springboot.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    protected Person person1;
    protected PersonRequest personRequest;

    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    private static Person generateInstancePerson(Long personId) {
        return new Person(personId, "John", "Carry", "john@email.com", "1 Street", "Male");
    }

    private static Stream<Arguments> findAllPersonsInputParams() {
        return Stream.of(
                Arguments.of(new PageImpl<>(new ArrayList<Person>() {{
                    add(generateInstancePerson(1L));
                }}), 1),
                Arguments.of(new PageImpl<>(new ArrayList<Person>()), 0)
        );
    }

    @BeforeEach
    void setup() {
        // Given
        person1 = generateInstancePerson(1L);
        personRequest = new PersonRequest(person1.getFirstName(), person1.getLastName(), person1.getEmail(), person1.getAddress(), person1.getGender());
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

    @DisplayName("Given Person List When Find All Persons By Search Query Null Then Return Persons List And Never Call Find All By Query")
    @ParameterizedTest
    @MethodSource("findAllPersonsInputParams")
    public void testGivenPersonList_WhenFindAllPersonsBySearchQueryNull_ThenReturnPersonsListAndNeverCallFindAllByQuery(
            Page<Person> personPage, int expectedAmountPersons
    ) {
        // Given
        String searchQuery = null;
        int page = 0;
        int size = 10;

        // When
        when(personRepository.findAll(any(PageRequest.class))).thenReturn(personPage);
        List<Person> receivedPersonList = personService.findAllPerson(page, size, searchQuery);

        // Then
        assertNotNull(receivedPersonList);
        verify(personRepository, never()).findAllByQuery(anyString(), any(PageRequest.class));
        assertEquals(expectedAmountPersons, receivedPersonList.size());
    }

    @DisplayName("Given Person List When Find All Persons By Search Query Null Then Return Persons List And Never Call Find All")
    @ParameterizedTest
    @MethodSource("findAllPersonsInputParams")
    public void testGivenPersonList_WhenFindAllPersonsBySearchQueryNull_ThenReturnPersonsListAndNeverCallFindAll(
            Page<Person> personPage, int expectedAmountPersons
    ) {
        // Given
        int page = 0;
        int size = 10;
        String searchQuery = "any-query";

        // When
        when(personRepository.findAllByQuery(anyString(), any(PageRequest.class))).thenReturn(personPage);
        List<Person> receivedPersonList = personService.findAllPerson(page, size, searchQuery);

        // Then
        assertNotNull(receivedPersonList);
        verify(personRepository, never()).findAll(any(PageRequest.class));
        assertEquals(expectedAmountPersons, receivedPersonList.size());
    }


    @DisplayName("Given Person Object When Partials Update Person Then Update Person")
    @Test
    public void testGivenPersonObject_WhenPartialsUpdatePerson_ThenUpdatePerson() {
        // When & Then
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person1));
        assertDoesNotThrow(() ->
                // When
                personService.partialsUpdatePerson(person1.getId(), personRequest)
        );

        // Then
        verify(personRepository).save(any(Person.class));
    }

    @DisplayName("Given Person Object When Partials Update Person With Not Found Person Then Throws Exception")
    @Test
    public void testGivenPersonObject_WhenPartialsUpdateWithNotFoundPerson_ThenThrowsException() {
        // When & Then
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(
                PersonNotFoundException.class,
                // When
                () -> personService.partialsUpdatePerson(person1.getId(), personRequest),
                () -> "person not found with id " + person1.getId()
        );

        // Then
        verify(personRepository, never()).save(any(Person.class));
    }
}
