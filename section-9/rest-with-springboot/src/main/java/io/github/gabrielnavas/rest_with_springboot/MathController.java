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

    @GetMapping("/average/{numbers}")
    public Double average(
            @PathVariable(value = "numbers") String numbers
    ) {
        if (numbers.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param numbers");
        }
        String[] numbersArray = numbers.split(",");
        if (numbersArray.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (String number : numbersArray) {
            sum += toDouble(number);
        }
        return sum / numbersArray.length;
    }

    @GetMapping("/sqrt/{number}")
    public Double sqrt(
            @PathVariable(value = "number") String number
    ) {
        if (number.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param number");
        }
        final Double numberDouble = toDouble(number);
        if (isNegative(numberDouble)) {
            throw new UnsupportedMathOperationException("unsupported number negative");
        }
        return Math.sqrt(toDouble(number));
    }

    @GetMapping("/pow/{numberOne}/{numberTwo}")
    public Double pow(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        if (numberOne.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param number one");
        }
        if (numberTwo.isEmpty()) {
            throw new UnsupportedMathOperationException("missing param number two");
        }
        return Math.pow(toDouble(numberOne), toDouble(numberTwo));
    }

    private boolean isNegative(Double number) {
        double epsilon = 1e-10;
        return number < epsilon;
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
