package io.github.gabrielnavas.rest_with_springboot.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * // GET ONE
 * curl -X GET http://localhost:8080/person/1
 *
 * // GET ALL WITH QUERY
 * curl -X GET http://localhost:8080/person?page=0\&size=1\&query="Carry"
 *
 *  * // GET ALL WITHOUT QUERY
 * curl -X GET http://localhost:8080/person?page=0\&size=10
 *
 * // GET ALL WITHOUT QUERY, PAGES AND SIZE
 * curl -X GET http://localhost:8080/person
 *
 * // POST
 * curl -X POST http://localhost:8080/person -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Carry", "address": " 1 Street", "gender":"Male"}'
 *
 * // PATCH
 * curl -X PATCH http://localhost:8080/person/1 -H "Content-Type: application/json" -d '{"firstName":"Mary", "lastName": "Laster", "address": "Here", "gender":"Woman"}'
 *
 * // DELETE
 * curl -X DELETE http://localhost:8080/person/1001
 * */


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
    public List<Person> findAllPerson(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "query", defaultValue = "") String searchQuery
    ) {
        return personService.findAllPerson(page, size, searchQuery);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
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

    @DeleteMapping(
            value = "/{person-id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(
            @PathVariable("person-id") Long personId
    ) {
        personService.deletePerson(personId);
    }
}
