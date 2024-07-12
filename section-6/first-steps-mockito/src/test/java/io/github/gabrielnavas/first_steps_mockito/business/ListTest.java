package io.github.gabrielnavas.first_steps_mockito.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ListTest {

    @DisplayName("Test Mocking When Size Is Called Should Return 10")
    @Test
    public void testMocking_When_SizeIsCalled_ShouldReturn10() {
        // Given / Arrange
        List<?> list = Mockito.mock(List.class);
        Mockito.when(list.size()).thenReturn(10);

        // When / Act
        int size = list.size();

        // Then / Assert
        Assertions.assertEquals(10, size);
    }

    @DisplayName("Test Mocking When Size Is Called Should Return Multiple Values")
    @Test
    public void testMocking_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = Mockito.mock(List.class);
        Mockito.when(list.size())
                .thenReturn(10)
                .thenReturn(20)
                .thenReturn(30);

        // When & Then / Assert
        Assertions.assertEquals(10, list.size());
        Assertions.assertEquals(20, list.size());
        Assertions.assertEquals(30, list.size());
    }

    @DisplayName("Test Mocking When Size Is Called Should Return String Navas")
    @Test
    public void testMocking_When_SizeIsCalled_ShouldReturnStringNavas() {
        // Given / Arrange
        List list = Mockito.mock(List.class);
        Mockito.when(list.get(0))
                .thenReturn("Navas");

        // When & Then / Assert
        Assertions.assertEquals("Navas", list.get(0));
        Assertions.assertNull(list.get(1));
    }

    @DisplayName("Test Mocking When Get Is Called With Argument Matcher Should Return String Navas Any Index")
    @Test
    public void testMocking_When_GetIsCalledWithArgumentMatcher_ShouldReturnStringNavas() {
        // Given / Arrange
        List list = Mockito.mock(List.class);
        Mockito.when(list.get(Mockito.anyInt()))
                .thenReturn("Navas");

        // When & Then / Assert
        Assertions.assertEquals("Navas", list.get(Mockito.anyInt()));
        Assertions.assertEquals("Navas", list.get(Mockito.anyInt()));
        Assertions.assertNotNull(list.get(Mockito.anyInt()));
    }

    @DisplayName("Test Mocking When Throws An Exception")
    @Test
    public void testMocking_When_ThrowsAnException() {
        // Given
        List list = Mockito.mock(List.class);
        Mockito.when(list.get(Mockito.anyInt()))
                .thenThrow(new RuntimeException("Something went wrong"));

        // Then
        Assertions.assertThrows(
                RuntimeException.class,
                // When
                () -> list.get(Mockito.anyInt()),
                () -> "runtime exception expected"
        );
    }
}
