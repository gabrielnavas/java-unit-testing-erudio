package io.github.gabrielnavas.automated_tests_with_java.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ArraysCompareTest {

    @Test
    void test() {
        // Given
        int[] numbers = {25, 8, 21, 32, 3};
        int[] expectedArray = {3, 8, 21, 25, 32};

        // When
        Arrays.sort(numbers);

        // Then
        Assertions.assertArrayEquals(expectedArray, numbers);
    }

    @Test
    @Timeout(value = 20, unit = TimeUnit.MILLISECONDS)
    void testSortPerformance() {
        // Given
        int[] numbers = {25, 8, 21, 32, 3};

        // When
        for (int i = 0; i < 1_000_000; i++) {
            numbers[0] = 0;
            Arrays.sort(numbers);
        }
    }
}
