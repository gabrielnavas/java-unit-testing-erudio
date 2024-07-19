package io.github.gabrielnavas.rest_with_springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private final static String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greet(
            @RequestParam(value = "name", defaultValue = "World") String name
    ) {
        final String content = String.format(template, name);
        return new Greeting(counter.incrementAndGet() + "", content);
    }
}
