package io.github.gabrielnavas.rest_with_springboot.math;

public class NumberVerify {

    public static boolean isZero(Double number) {
        double epsilon = 1e-10;
        return Math.abs(number) < epsilon;
    }

    public static boolean isNegative(Double number) {
        double epsilon = 1e-10;
        return number < epsilon;
    }

    public static boolean isNumeric(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
