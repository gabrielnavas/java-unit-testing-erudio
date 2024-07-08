package io.github.gabrielnavas.automated_tests_with_java.math;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

    @Test
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
    public void testSubtract() {
        SimpleMath math = new SimpleMath();
        double first = 4;
        double second = 2;
        Double actual = math.subtract(first, second);
        double expected = 2.0D;
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.subtract(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }


    @Test
    public void testMultiply() {
        SimpleMath math = new SimpleMath();
        double first = 4;
        double second = 2;
        Double actual = math.multiply(first, second);
        double expected = 8.0D;
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.multiply(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }

    @Test
    public void testDivide() {
        SimpleMath math = new SimpleMath();
        double first = 4;
        double second = 2;
        Double actual = math.divide(first, second);
        double expected = 2.0D;
        Assertions.assertEquals(
                expected,
                actual,
                () -> String.format("The math.multiply(%.2f, %.2f)=%.2f did not produced expected value %.2f.",
                        first, second, actual, expected)
        );
    }

    @Test
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
    public void testMean() {
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
