package io.github.gabrielnavas.automated_tests_with_java.math;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    @Test
    @DisplayName("6.2 + 2 = 8.2")
    public void testSum() {
        SimpleMath math = new SimpleMath();
        double first = 6.2D;
        double second = 2D;
        Double actual = math.sum(first, second);
        double expected = 8.2D;
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
        SimpleMath math = new SimpleMath();
        double first = 6.2;
        double second = 2.0;
        Double actual = math.subtract(first, second);
        double expected = 4.2D;
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
        SimpleMath math = new SimpleMath();
        double first = 6;
        double second = 2;
        Double actual = math.multiply(first, second);
        double expected = 12.0D;
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
        SimpleMath math = new SimpleMath();
        double first = 6;
        double second = 2;
        Double actual = math.divide(first, second);
        double expected = 3.0D;
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.divide(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }

    @Test
    @DisplayName("Test division by Zero")
    public void testDivide_When_FourIsDivideByZero_ShouldReturnTwo() {
        SimpleMath math = new SimpleMath();
        double first = 4;
        double second = 0;
        Exception expected = new ArithmeticException("Divide by zero");
        Exception actual = Assertions.assertThrows(
                ArithmeticException.class,
                () -> math.divide(first, second)

        );

        Assertions.assertInstanceOf(ArithmeticException.class, actual, () -> String.format(
                "actual should by instance of %s", ArithmeticException.class.getName()
        ));

        Assertions.assertEquals(expected.getMessage(), actual.getMessage(),
                () -> String.format(
                        "The math.divide(%.2f, %.2f) is not possible, because actual is zero.",
                        first, second
                )
        );
    }

    @Test
    @DisplayName("Root Square(81) = 9")
    public void testSqrt() {
        SimpleMath math = new SimpleMath();
        double n = 81;
        Double actual = math.sqrt(n);
        double expected = 9.0D;
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
        SimpleMath math = new SimpleMath();
        double first = 4;
        double second = 2;
        Double actual = math.mean(first, second);
        double expected = 3.0D;
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.mean(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }
}
