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

    List<Person> people = new ArrayList<>();

    public PersonService() {
        for (int i = 0; i < 1000; i++) {
            people.add(mockPerson((i + 1L)));
        }
    }

    public Person findPersonById(Long id) {
        logger.info("Finding one person!");

        for (Person person : people) {
            if (person.getId().equals(id)) {
                return person;
            }
        }

        throw new RuntimeException("person not found");
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
        List<Person> data = new ArrayList<>();
        int init = page * size;
        int end = (page * size) + size;
        for (int i = init; i < people.size() && i < end; i++) {
            data.add(people.get(i));
        }

        return data;
    }

    public Person createPerson(PersonRequest request) {
        final Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setAddress("São Paulo - SP " + person.getId());
        person.setFirstName("John " + person.getId());
        person.setLastName("Silva " + person.getId());
        person.setGender("Male " + person.getId());
        people.add(person);
        return person;
    }


    public void partialsUpdatePerson(Long personId, PersonRequest request) {
        Person person = null;
        for (Person value : people) {
            if (value.getId().equals(personId)) {
                person = value;
            }
        }

        if (person == null) {
            throw new RuntimeException("person not found");
        }

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setGender(request.getGender());
        person.setAddress(request.getAddress());
    }

    public void deletePerson(Long personId) {
        Person person = null;
        for (Person value : people) {
            if (value.getId().equals(personId)) {
                person = value;
            }
        }

        if (person == null) {
            throw new RuntimeException("person not found");
        }

        people.remove(person);
    }
}
