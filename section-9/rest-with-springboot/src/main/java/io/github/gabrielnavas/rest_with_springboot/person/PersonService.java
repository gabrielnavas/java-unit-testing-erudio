package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        person.setAddress("São Paulo - SP ");
        person.setFirstName("John");
        person.setLastName("Silva");
        person.setGender("Male");

        return person;
    }

    public Person mockPerson(Long index) {
        final Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setAddress("São Paulo - SP " + index);
        person.setFirstName("John " + index);
        person.setLastName("Silva " + index);
        person.setGender("Male " + index);
        return person;
    }

    public List<Person> findAllPerson(int page, int size) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(mockPerson((i + 1L)));
        }

        List<Person> data = new ArrayList<>();
        int init = page * size;
        int end = (page * size) + size;
        for (int i = init; i < people.size() && i < end; i++) {
            data.add(people.get(i));
        }

        return data;
    }
}
