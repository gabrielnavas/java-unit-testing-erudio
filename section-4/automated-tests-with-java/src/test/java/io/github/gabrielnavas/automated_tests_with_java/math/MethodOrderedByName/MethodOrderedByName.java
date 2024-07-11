package io.github.gabrielnavas.automated_tests_with_java.math.MethodOrderedByName;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MethodOrderedByName {
    @Test
    void testD() {
        System.out.println("Test D");
    }

    @Test
    void testC() {
        System.out.println("Test C");
    }

    @Test
    void testB() {
        System.out.println("Test B");
    }

    @Test
    void testA() {
        System.out.println("Test A");
    }
}
