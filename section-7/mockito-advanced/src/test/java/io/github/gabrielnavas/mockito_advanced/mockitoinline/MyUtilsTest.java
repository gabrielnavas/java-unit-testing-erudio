package io.github.gabrielnavas.mockito_advanced.mockitoinline;

import io.github.gabrielnavas.mockito_advanced.service.MyUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

public class MyUtilsTest {

    @DisplayName("Should mock Static Method With Params")
    @Test
    public void testShouldMockStaticMethodWithParams() {
        // Given / Arrange
        final String username = "John";
        try (MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            mockedStatic.when(() -> MyUtils.getWelcomeMessage(eq(username), anyBoolean()))
                    .thenReturn("Hello John!");

            // When & Then
            String result = MyUtils.getWelcomeMessage(username, false);
            assertEquals("Hello John!", result);
        }
    }
}
