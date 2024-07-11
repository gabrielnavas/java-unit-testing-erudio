package io.github.gabrielnavas.automated_tests_tdd;

import io.github.gabrielnavas.automated_tests_tdd.person.IPersonService;
import io.github.gabrielnavas.automated_tests_tdd.person.Person;
import io.github.gabrielnavas.automated_tests_tdd.person.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PersonServiceTest {

    Person person;
    IPersonService service;

    @BeforeEach
    void setup() {
        person = new Person(
                1L,
                "John",
                "Neth",
                "john@email.com",
                "12 Street - Uk",
                "Male"
        );
        service = new PersonService();
    }

    @DisplayName("When Create a Person with Success Should Return a Person Object With Not Null Field")
    @Test
    public void testCreatePerson_WhenSuccess_ShouldReturnPersonObjectWithNotNullField() {
        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertNotNull(actual, () -> "Person object should not be null");
        Assertions.assertNotNull(actual.getId(), () -> "Person ID is missing");
        Assertions.assertNotNull(actual.getFirstname(), () -> "Person firstname is missing");
        Assertions.assertNotNull(actual.getLastname(), () -> "Person lastname is missing");
        Assertions.assertNotNull(actual.getEmail(), () -> "Person email is missing");
        Assertions.assertNotNull(actual.getStreet(), () -> "Person street is missing");
        Assertions.assertNotNull(actual.getGender(), () -> "Person gender is missing");
    }

    @DisplayName("When Create a Person with Success Should Contains Valid Fields In Returned a Person Object")
    @Test
    public void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsInReturnedPersonObject() {
        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertEquals(actual.getFirstname(), person.getFirstname(), () -> "The Person FirstName is Incorrect");
        Assertions.assertEquals(actual.getLastname(), person.getLastname(), () -> "The Person LastName is Incorrect");
        Assertions.assertEquals(actual.getEmail(), person.getEmail(), () -> "The Person Email is Incorrect");
        Assertions.assertEquals(actual.getStreet(), person.getStreet(), () -> "The Person Street is Incorrect");
        Assertions.assertEquals(actual.getGender(), person.getGender(), () -> "The Person Gender is Incorrect");
    }


    @DisplayName("When Create a Person with Null Email Should Throws Exception")
    @ParameterizedTest
    @MethodSource("testEmailInvalidParameters")
    public void testCreatePerson_WhichNullEmail_ShouldThrowsIllegalArgumentException(String email, String expectedMessage) {
        // Given / Arrange
        person = new Person(
                1L,
                "John",
                "Neth",
                email,
                "12 Street - Uk",
                "Male"
        );

        // When & Then
        Exception actual = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.createPerson(person),
                () -> String.format("The Person Email Should throws a %s exception", IllegalArgumentException.class.getName()));

        // Then
        Assertions.assertEquals(
                expectedMessage,
                actual.getMessage(),
                () -> String.format("The Person Email Should return a %s exception", IllegalArgumentException.class.getName())
        );
    }


    public static Stream<Arguments> testEmailInvalidParameters() {
        return Stream.of(
                Arguments.of(null, "Email is null"),
                Arguments.of("", "Email is empty"),
                Arguments.of(" ", "Email is empty")
        );
    }

}

