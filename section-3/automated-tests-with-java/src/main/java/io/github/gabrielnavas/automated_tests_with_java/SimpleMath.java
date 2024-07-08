package io.github.gabrielnavas.automated_tests_with_java;

public class SimpleMath {

    public Double sum(Double first, Double second) {
        return first + second;
    }

    public Double subtract(Double first, Double second) {
        return first - second;
    }

    public Double multiply(Double first, Double second) {
        return first * second;
    }

    public Double divide(Double first, Double second) {
        if (second == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return first / second;
    }

    public Double mean(Double first, Double second) {
        return (first + second) / 2;
    }

    public Double sqrt(Double number) {
        return Math.sqrt(number);
    }
}
