package io.github.gabrielnavas.automated_tests_with_java.math.OrderClassTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassThree {

    @Test
    public void test() {
        System.out.println("ClassThree called");
    }
}
