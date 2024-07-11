package io.github.gabrielnavas.automated_tests_with_java.math.RepeatedTest;

import io.github.gabrielnavas.automated_tests_with_java.SimpleMath;
import org.junit.jupiter.api.*;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

    SimpleMath math;

    @BeforeEach
    void beforeEach() {
        math = new SimpleMath();
    }

    @DisplayName("test division by Zero")
    @RepeatedTest(value = 3)
    public void testDivideByZero(
            RepetitionInfo repetitionInfo,
            TestInfo testInfo
    ) {
        System.out.printf(
                "running %s - current repetition %d of %d\n",
                testInfo.getTestMethod().get().getName(),
                repetitionInfo.getCurrentRepetition(),
                repetitionInfo.getTotalRepetitions()
        );

        // Give
        double firstNumber = 10;
        double secondNumber = 0;
        String expectedMessage = "Impossible to divide by zero";

        // Then
        ArithmeticException expected = Assertions.assertThrows(
                ArithmeticException.class,
                // When
                () -> math.divide(firstNumber, secondNumber),
                () -> String.format("The math.divide(%.2f, %.2f) did not produced throws %s",
                        firstNumber, secondNumber, ArithmeticException.class.getName())
        );

        // Then
        Assertions.assertEquals(expected.getMessage(), expectedMessage);
    }
}
