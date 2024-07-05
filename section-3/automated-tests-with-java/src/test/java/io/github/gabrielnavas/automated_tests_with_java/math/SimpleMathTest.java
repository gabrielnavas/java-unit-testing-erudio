package io.github.gabrielnavas.automated_tests_with_java.math;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

    @Test
    public void test() {
        SimpleMath math = new SimpleMath();
        Double actual = math.sum(6.2D, 2D);
        double expected = 8.2D;
        Assertions.assertEquals(expected, actual);
    }
}
