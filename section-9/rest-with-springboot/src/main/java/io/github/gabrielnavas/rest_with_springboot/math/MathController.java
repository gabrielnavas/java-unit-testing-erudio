package io.github.gabrielnavas.rest_with_springboot.math;

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
        return SimpleMath.sum(numberOne, numberTwo);
    }

    @GetMapping("/subtract/{numberOne}/{numberTwo}")
    public Double subtract(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return SimpleMath.subtract(
                numberOne,
                numberTwo
        );
    }

    @GetMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiply(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return SimpleMath.multiply(
                numberOne,
                numberTwo
        );
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return SimpleMath.division(
                numberOne,
                numberTwo
        );
    }

    @GetMapping("/mean/{numbers}")
    public Double mean(
            @PathVariable(value = "numbers") String numbers
    ) {
        return SimpleMath.mean(numbers);
    }

    @GetMapping("/sqrt/{number}")
    public Double sqrt(
            @PathVariable(value = "number") String number
    ) {
        return SimpleMath.sqrt(number);
    }

    @GetMapping("/pow/{numberOne}/{numberTwo}")
    public Double pow(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        return SimpleMath.pow(numberOne, numberTwo);
    }

}
