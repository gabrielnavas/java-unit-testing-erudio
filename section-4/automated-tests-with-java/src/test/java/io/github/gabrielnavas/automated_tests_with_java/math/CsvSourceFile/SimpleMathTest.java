package io.github.gabrielnavas.automated_tests_with_java.math.CsvSourceFile;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    SimpleMath math;

    @BeforeEach
    void beforeEach() {
        math = new SimpleMath();
    }

    @DisplayName("test subtraction [first, second, expected]")
    @ParameterizedTest
    @CsvFileSource(resources = "/testDivision.csv")
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