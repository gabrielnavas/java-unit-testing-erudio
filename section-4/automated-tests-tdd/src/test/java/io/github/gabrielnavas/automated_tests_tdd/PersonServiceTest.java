package io.github.gabrielnavas.automated_tests_tdd;

import io.github.gabrielnavas.automated_tests_tdd.person.IPersonService;
import io.github.gabrielnavas.automated_tests_tdd.person.Person;
import io.github.gabrielnavas.automated_tests_tdd.person.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    public void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        // Given / Arrange
        IPersonService service = new PersonService();
        Person person = new Person(
                1L,
                "John",
                "Neth",
                "john@email.com",
                "12 Street - Uk",
                "Male"
        );

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        Assertions.assertNotNull(actual, () -> "Person object should not be null");
    }

}
