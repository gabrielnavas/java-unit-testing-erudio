package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public Person findPersonById(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("person not found"));
    }

    public List<Person> findAllPerson(
            int page, int size, String searchQuery
    ) {
        logger.info("Finding people!");

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.DESC,
                "id");

        if (searchQuery == null || searchQuery.isEmpty()) {
            return personRepository.findAll(pageRequest).stream().toList();
        }
        return personRepository.findAllByQuery(searchQuery, pageRequest)
                .stream()
                .toList();
    }

    public Person createPerson(PersonRequest request) {
        logger.info("Create a Person!");

        Optional<Person> personOptionalEmail = personRepository.findByEmail(request.getEmail());
        if (personOptionalEmail.isPresent()) {
            throw new RuntimeException("person already exists with email");
        }

        final Person person = new Person();
        person.setAddress(request.getAddress());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setGender(request.getGender());

        return personRepository.save(person);
    }

    public void partialsUpdatePerson(Long personId, PersonRequest request) {
        logger.info("Partials update a Person!");

        Person person = personRepository.findById(personId).orElseThrow(() -> new RuntimeException("person not found"));

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setGender(request.getGender());
        person.setAddress(request.getAddress());

        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        logger.info("Delete a Person!");
        Person person = personRepository.findById(personId).orElseThrow(() -> new RuntimeException("person not found"));
        personRepository.delete(person);
    }
}
