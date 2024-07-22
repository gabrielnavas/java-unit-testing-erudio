package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{person-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findPersonById(
            @PathVariable("person-id") String personId
    ) {
        Long id = Long.parseLong(personId);
        return personService.findPersonById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findPersonById(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return personService.findAllPerson(page, size);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestBody PersonRequest request) {
        return personService.createPerson(request);
    }

    @PatchMapping(
            value = "/{person-id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void partialsUpdatePerson(
            @PathVariable("person-id") Long personId,
            @RequestBody PersonRequest request
    ) {
        personService.partialsUpdatePerson(personId, request);
    }
}
