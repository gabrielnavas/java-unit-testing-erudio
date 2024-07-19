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
