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
}
