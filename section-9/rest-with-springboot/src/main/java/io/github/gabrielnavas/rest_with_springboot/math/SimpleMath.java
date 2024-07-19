package io.github.gabrielnavas.rest_with_springboot.math;

public class SimpleMath {


    public static Double sum(String n1, String n2) {
        return NumberConverter.toDouble(n1) + NumberConverter.toDouble(n2);
    }

    public static Double subtract(String n1, String n2) {
        return NumberConverter.toDouble(n1) - NumberConverter.toDouble(n2);
    }

    public static Double multiply(String n1, String n2) {
        return NumberConverter.toDouble(n1) * NumberConverter.toDouble(n2);
    }

    public static Double division(String n1, String n2) {
        Double numberTwoDouble = NumberConverter.toDouble(n2);
        if (NumberVerify.isZero(numberTwoDouble)) {
            throw new UnsupportedMathOperationException("Division by zero is not supported");
        }

        return NumberConverter.toDouble(n1) / numberTwoDouble;
    }

    public static Double mean(String numbers) {
        if (numbers.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param numbers");
        }
        String[] numbersArray = numbers.split(",");
        if (numbersArray.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (String number : numbersArray) {
            sum += NumberConverter.toDouble(number);
        }
        return sum / numbersArray.length;
    }

    public static Double sqrt(String number) {
        if (number.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param number");
        }
        final Double numberDouble = NumberConverter.toDouble(number);
        if (NumberVerify.isNegative(numberDouble)) {
            throw new UnsupportedMathOperationException("unsupported number negative");
        }
        return Math.sqrt(NumberConverter.toDouble(number));
    }

    public static Double pow(String n1, String n2) {
        if (n1.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param number one");
        }
        if (n2.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param number two");
        }
        return Math.pow(NumberConverter.toDouble(n1), NumberConverter.toDouble(n2));
    }
}
