package io.github.gabrielnavas.automated_tests_with_java.math.LivecyclePerClass;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD) PER_METHOD IS DEFAULT
public class WithoutLifecyclePerClass {

    /*
     * Each method called is a new instance of this class
     * */

    StringBuilder actualValue = new StringBuilder();

    @AfterEach
    public void afterEach() {
        System.out.println("The actual value is: " + actualValue.toString());
    }

    @Test
    @Order(3)
    void testD() {
        System.out.println("Test D");
        actualValue.append("3");
    }

    @Test
    @Order(2)
    void testC() {
        System.out.println("Test C");
        actualValue.append("2");
    }

    @Test
    @Order(1)
    void testB() {
        System.out.println("Test B");
        actualValue.append("1");
    }

    @Test
    @Order(0)
    void testA() {
        System.out.println("Test A");
        actualValue.append("0");
    }
}
