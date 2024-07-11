package io.github.gabrielnavas.automated_tests_with_java.math.ParameterizedTest;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    SimpleMath math;

    public static Stream<Arguments> testDivideInputParameters() {
        return Stream.of(
                Arguments.of(10, 2, 5),
                Arguments.of(15, 3, 5),
                Arguments.of(50, 10, 5)
        );
    }

    @BeforeEach
    void beforeEach() {
        math = new SimpleMath();
    }

    @DisplayName("x / y = z")
    @ParameterizedTest
    @MethodSource("testDivideInputParameters")
    public void testDivide(double first, double second, double expected) {
        // When
        Double actual = math.divide(first, second);

        // Then
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.divide(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }
}
