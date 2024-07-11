package io.github.gabrielnavas.automated_tests_with_java.math.OrderClassTest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(3)
public class ClassTwo {

    @Test
    public void test() {
        System.out.println("ClassTwo called");
    }
}
