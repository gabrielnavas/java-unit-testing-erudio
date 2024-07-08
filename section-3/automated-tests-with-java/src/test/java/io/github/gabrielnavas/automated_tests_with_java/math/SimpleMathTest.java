package io.github.gabrielnavas.automated_tests_with_java.math;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    @Test
    @DisplayName("6.2 + 2 = 8.2")
    public void testSum() {
        // Given / Arrange
        SimpleMath math = new SimpleMath();
        double first = 6.2D;
        double second = 2D;
        double expected = 8.2D;

        // When / Act
        Double actual = math.sum(first, second);

        // Then / Assert
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.sum(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }

    @Test
    @DisplayName("6.2 + 2.0 = 4.2")
    public void testSubtract() {
        // Given
        SimpleMath math = new SimpleMath();
        double first = 6.2;
        double second = 2.0;
        double expected = 4.2D;

        // When
        Double actual = math.subtract(first, second);

        // Then
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.subtract(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }


    @Test
    @DisplayName("6.0 * 2.0 = 12.0")
    public void testMultiply() {
        // Give
        SimpleMath math = new SimpleMath();
        double first = 6;
        double second = 2;
        double expected = 12.0D;

        // When
        Double actual = math.multiply(first, second);

        // Then
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.multiply(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }

    @Test
    @DisplayName("6.0 / 2.0 = 3.0")
    public void testDivide() {
        // Give
        SimpleMath math = new SimpleMath();
        double first = 6;
        double second = 2;
        double expected = 3.0D;

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

    @Disabled("TODO: We need still work on it!!")
    @Test
    @DisplayName("Test division by Zero")
    public void testDivide_When_FourIsDivideByZero_ShouldReturnTwo() {
        fail();
    }

    @Test
    @DisplayName("Root Square(81) = 9")
    public void testSqrt() {
        // Give
        SimpleMath math = new SimpleMath();
        double n = 81;
        double expected = 9.0D;

        // When
        Double actual = math.sqrt(n);

        // Then
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.sqrt(%.2f)=%.2f did not produced expected value %.2f.",
                        n, actual, expected)
        );
    }

    @Test
    @DisplayName("(4.0 + 2.0) / 2 = 3")
    public void testMean_When_MeanFourAndTwo_ShouldReturnThree() {
        // Give
        SimpleMath math = new SimpleMath();
        double first = 4;
        double second = 2;
        double expected = 3.0D;

        // When
        Double actual = math.mean(first, second);

        // Then
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.mean(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }
}
