package io.github.gabrielnavas.test_coverage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Using IntelliJ, execute 'Run Test with Coverage'.
    It shows you the code coverage!
 */


public class TestServiceTest {

    @Test
    public void test() {
        TestService testService = new TestService();
        String result = testService.doAnything(null);
        assertEquals(result, null);
    }
}
