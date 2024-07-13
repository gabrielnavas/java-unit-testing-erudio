package io.github.gabrielnavas.mockito_advanced.mockito;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {
    @Test
    public void testIntWithIntervals() {
        // Given
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

        // When & Then
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 105));
        assertThat(scores, everyItem(greaterThan(98)));
        assertThat(scores, everyItem(greaterThanOrEqualTo(99)));
        assertThat(scores, everyItem(lessThan(106)));
        assertThat(scores, everyItem(lessThanOrEqualTo(105)));
    }

    @Test
    public void testStringWithIntervals() {
        // Given
        String emptyString = "";
        String nullString = null;

        // When & Then
        assertThat(emptyString, is(emptyString()));
        assertThat(nullString, is(emptyOrNullString()));
    }


    @Test
    public void testArrays() {
        // Given
        Integer[] myArray = {1, 2, 3};

        // When & Then
        assertThat(myArray, arrayWithSize(3));
        assertThat(myArray, arrayContaining(1, 2, 3));
        assertThat(myArray, arrayContainingInAnyOrder(2, 1, 3));
    }
}
