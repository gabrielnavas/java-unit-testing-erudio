package io.github.gabrielnavas.rest_with_springboot.math;

public class NumberConverter {

    public static Double toDouble(String number) {
        if (number == null || number.isEmpty()) {
            return 0.0;
        }
        final String americanFormat = number.replaceAll(",", ".");
        if (!NumberVerify.isNumeric(americanFormat)) {
            throw new IllegalArgumentException("param is not a number");
        }
        return Double.parseDouble(americanFormat);
    }

}
