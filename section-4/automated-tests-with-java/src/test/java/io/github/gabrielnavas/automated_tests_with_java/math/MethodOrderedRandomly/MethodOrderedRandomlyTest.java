package io.github.gabrielnavas.automated_tests_with_java.math.MethodOrderedRandomly;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public class MethodOrderedRandomlyTest {

    @Test
    void testA() {
        System.out.println("Test A");
    }

    @Test
    void testB() {
        System.out.println("Test B");
    }

    @Test
    void testC() {
        System.out.println("Test C");
    }

    @Test
    void testD() {
        System.out.println("Test D");
    }

}
