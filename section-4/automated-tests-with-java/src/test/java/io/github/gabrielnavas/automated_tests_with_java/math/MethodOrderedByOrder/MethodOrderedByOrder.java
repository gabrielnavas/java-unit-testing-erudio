package io.github.gabrielnavas.automated_tests_with_java.math.MethodOrderedByOrder;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderedByOrder {
    @Test
    @Order(3)
    void testD() {
        System.out.println("Test D");
    }

    @Test
    @Order(2)
    void testC() {
        System.out.println("Test C");
    }

    @Test
    @Order(1)
    void testB() {
        System.out.println("Test B");
    }

    @Test
    @Order(0)
    void testA() {
        System.out.println("Test A");
    }
}
