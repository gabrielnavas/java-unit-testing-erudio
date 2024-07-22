package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findPersonById(Long id) {
        logger.info("Finding one person!");

        final Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setAddress("São Paulo - SP");
        person.setFirstName("John");
        person.setLastName("Silva");
        person.setGender("Male");

        return person;
    }
}
