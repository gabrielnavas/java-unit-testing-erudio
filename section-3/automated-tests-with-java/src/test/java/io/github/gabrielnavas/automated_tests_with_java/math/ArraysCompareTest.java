package io.github.gabrielnavas.automated_tests_with_java.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
}
