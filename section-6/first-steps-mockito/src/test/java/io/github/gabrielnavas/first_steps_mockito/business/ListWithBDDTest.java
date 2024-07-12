package io.github.gabrielnavas.first_steps_mockito.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

public class ListWithBDDTest {

    List<String> list;

    @BeforeEach
    public void setup() {
        list = Mockito.mock(List.class);
    }

    @DisplayName("Test Mocking When Size Is Called Should Return 10")
    @Test
    public void testMocking_When_SizeIsCalled_ShouldReturn10() {
        // Given / Arrange
        given(list.size()).willReturn(10);

        // When / Act
        int size = list.size();

        // Then / Assert
        assertThat(size, is(10));
    }

    @DisplayName("Test Mocking When Size Is Called Should Return Multiple Values")
    @Test
    public void testMocking_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        given(list.size())
                .willReturn(10)
                .willReturn(20)
                .willReturn(30);

        // When & Then / Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
        assertThat(list.size(), is(30));
    }

    @DisplayName("Test Mocking When Size Is Called Should Return String Navas")
    @Test
    public void testMocking_When_SizeIsCalled_ShouldReturnStringNavas() {
        // Given / Arrange
        given(list.get(0))
                .willReturn("Navas");


        // When & Then / Assert
        assertThat(list.get(0), is("Navas"));
        assertThat(list.get(1), nullValue());
    }

    @DisplayName("Test Mocking When Get Is Called With Argument Matcher Should Return String Navas Any Index")
    @Test
    public void testMocking_When_GetIsCalledWithArgumentMatcher_ShouldReturnStringNavas() {
        // Given / Arrange
        given(list.get(Mockito.anyInt()))
                .willReturn("Navas");

        // When & Then / Assert
        assertThat(list.get(Mockito.anyInt()), is("Navas"));
        assertThat(list.get(Mockito.anyInt()), is("Navas"));
        assertThat(list.get(Mockito.anyInt()), notNullValue());
    }

    @DisplayName("Test Mocking When Throws An Exception")
    @Test
    public void testMocking_When_ThrowsAnException() {
        // Given
        given(list.get(Mockito.anyInt()))
                .willThrow(new RuntimeException("Something went wrong"));

        // Then
        RuntimeException actual = assertThrows(
                RuntimeException.class,
                // When
                () -> list.get(Mockito.anyInt())
        );

        assertThat(actual.getMessage(), is("Something went wrong"));
    }
}
