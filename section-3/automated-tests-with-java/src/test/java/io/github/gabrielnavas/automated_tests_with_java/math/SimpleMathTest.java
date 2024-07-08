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
}
