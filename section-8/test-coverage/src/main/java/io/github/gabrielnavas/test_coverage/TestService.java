package io.github.gabrielnavas.test_coverage;

public class TestService {

    public String doAnything(String anyParam) {
        if(anyParam == null) {
            return null;
        }

        return anyParam;
    }

    public String fooBar(String someParam) {
        if(someParam == null) {
            return null;
        }
        return someParam;
    }
}
