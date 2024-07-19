package io.github.gabrielnavas.rest_with_springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final static String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return toDouble(numberOne) + toDouble(numberTwo);
    }

    @GetMapping("/subtract/{numberOne}/{numberTwo}")
    public Double subtract(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return toDouble(numberOne) - toDouble(numberTwo);
    }

    @GetMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiply(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return toDouble(numberOne) * toDouble(numberTwo);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        Double numberTwoDouble = toDouble(numberTwo);
        if (isZero(numberTwoDouble)) {
            throw new UnsupportedMathOperationException("Division by zero is not supported");
        }

        return toDouble(numberOne) / numberTwoDouble;
    }

    private boolean isZero(Double number) {
        double epsilon = 1e-10;
        return Math.abs(number) < epsilon;
    }

    private Double toDouble(String number) {
        if (number == null || number.isEmpty()) {
            return 0.0;
        }
        final String americanFormat = number.replaceAll(",", ".");
        if (!isNumeric(americanFormat)) {
            throw new IllegalArgumentException("param is not a number");
        }
        return Double.parseDouble(americanFormat);
    }

    private boolean isNumeric(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
