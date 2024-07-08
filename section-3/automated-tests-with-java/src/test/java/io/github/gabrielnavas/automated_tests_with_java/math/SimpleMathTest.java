package io.github.gabrielnavas.automated_tests_with_java.math;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.*;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    SimpleMath math;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll method. Create database, insert data etc.");
    }

    @AfterAll
    static void clearUp() {
        System.out.println("@AfterAll method. Clear database.");
    }

    @BeforeEach
    void beforeEach() {
        math = new SimpleMath();
        System.out.println("@BeforeEach method. Init objects for example.");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach method.");
    }

    @Test
    @DisplayName("6.2 + 2 = 8.2")
    public void testSum() {
        // Given / Arrange

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

    @Test
    @DisplayName("Test division by Zero")
    public void testDivide_divisionByZero() {
        // Give
        double firstNumber = 10;
        double secondNumber = 0;
        String expectedMessage = "Impossible to divide by zero";

        // When & Then
        ArithmeticException actual = Assertions.assertThrows(ArithmeticException.class, () ->
                        // When
                        math.divide(firstNumber, secondNumber),
                () -> "Division by zero should throw ArithmeticException"
        );

        // Then
        Assertions.assertEquals(expectedMessage, actual.getMessage(), () -> "Unexpected exception message");
    }

    @Test
    @DisplayName("Root Square(81) = 9")
    public void testSqrt() {
        // Give
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
