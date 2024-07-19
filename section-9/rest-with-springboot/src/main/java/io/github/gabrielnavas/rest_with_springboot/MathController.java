package io.github.gabrielnavas.rest_with_springboot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final static String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(
            value = "/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {

        if (!isNumeric(numberOne)) {
            throw new IllegalArgumentException("param one is not a number");
        }
        if (!isNumeric(numberTwo)) {
            throw new IllegalArgumentException("param two is not a number");
        }
        return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
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
