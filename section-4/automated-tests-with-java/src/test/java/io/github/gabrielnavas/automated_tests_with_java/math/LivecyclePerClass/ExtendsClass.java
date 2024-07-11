package io.github.gabrielnavas.automated_tests_with_java.math.LivecyclePerClass;

import org.junit.jupiter.api.Test;

public class ExtendsClass extends WithLifecyclePerClass {

    @Test
    public void testExtendsClass() {
        System.out.println("testExtendsClass called with actual value " + actualValue);
    }
}
