package io.github.gabrielnavas.rest_with_springboot.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    private PersonRequest personRequest;
    private Person person;

    public static Stream<Arguments> findAllInputPath() {
        return Stream.of(
                Arguments.of("/person"),
                Arguments.of("/person?size=10"),
                Arguments.of("/person?size=1"),
                Arguments.of("/person?page=0"),
                Arguments.of("/person?page=0&size=10"),
                Arguments.of("/person?page=0&size=1")
        );
    }

    @BeforeEach
    void setup() {
        personRequest = new PersonRequest("John", "Taylor", "john@email.com", "1 Street", "Male");
        person = new Person(1L, personRequest.getFirstName(), personRequest.getLastName(), personRequest.getEmail(), personRequest.getAddress(), personRequest.getGender());
    }

    @DisplayName("given person request when create person then return saved person")
    @Test
    void testGivenPersonRequest_WhenCreatePerson_thenReturnSavedPerson() throws Exception {
        // Given
        when(personService.createPerson(any(PersonRequest.class))).thenReturn(person);

        // When
        ResultActions resultActions = mockMvc.perform(
                post("/person").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personRequest))
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(personRequest.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(personRequest.getLastName())))
                .andExpect(jsonPath("$.email", is(personRequest.getEmail())))
                .andExpect(jsonPath("$.address", is(personRequest.getAddress())))
                .andExpect(jsonPath("$.gender", is(personRequest.getGender())));
    }

    @DisplayName("given list persons when find all by query then return list persons")
    @ParameterizedTest
    @MethodSource("findAllInputPath")
    void testGivenListPersons_WhenFindAllByQuery_thenReturnListPersons(String path) throws Exception {
        // Given
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        // When
        when(personService.findAllPerson(anyInt(), anyInt(), anyString())).thenReturn(personList);
        ResultActions resultActions = mockMvc.perform(
                get(path).accept(MediaType.APPLICATION_JSON)
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(personList.size())))
                .andExpect(jsonPath("$[0].firstName", is(personRequest.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(personRequest.getLastName())))
                .andExpect(jsonPath("$[0].email", is(personRequest.getEmail())))
                .andExpect(jsonPath("$[0].address", is(personRequest.getAddress())))
                .andExpect(jsonPath("$[0].gender", is(personRequest.getGender())));
    }

    @DisplayName("given person object when find person by id then return list persons")
    @Test
    void testGivenPersonObject_WhenFindPersonById_thenReturnPersonObject() throws Exception {
        // When
        when(personService.findPersonById(person.getId())).thenReturn(person);
        ResultActions resultActions = mockMvc.perform(
                get("/person/" + person.getId()).accept(MediaType.APPLICATION_JSON)
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(personRequest.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(personRequest.getLastName())))
                .andExpect(jsonPath("$.email", is(personRequest.getEmail())))
                .andExpect(jsonPath("$.address", is(personRequest.getAddress())))
                .andExpect(jsonPath("$.gender", is(personRequest.getGender())));
    }

    @DisplayName("given person object when find not found person by id then return throws exception")
    @Test
    void testGivenPersonObject_whenFindNotFoundPersonById_thenReturnThrowsException() throws Exception {
        // When
        when(personService.findPersonById(person.getId())).thenThrow(new PersonNotFoundException("id", personRequest.getEmail()));
        ResultActions resultActions = mockMvc.perform(
                get("/person/{id}", person.getId())
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("person not found with id john@email.com")));
    }

    @DisplayName("given person object when delete person then return list persons")
    @Test
    void testGivenPersonObject_whenDeletePerson_thenRemovePerson() throws Exception {
        // When
        ResultActions resultActions = mockMvc.perform(
                delete("/person/{id}", person.getId())
        );

        // Then
        resultActions.andDo(print()).andExpect(status().isNoContent());
    }

    @DisplayName("given person object when delete not found person then throws exception")
    @Test
    void testGivenPersonObject_whenDeleteNotFound_thenThrowsException() throws Exception {
        // When
        doThrow(
                new PersonNotFoundException("id", personRequest.getEmail())
        ).when(personService).deletePerson(person.getId());

        ResultActions resultActions = mockMvc.perform(
                delete("/person/{id}", person.getId())
        );

        // Then
        resultActions.andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("person not found with id john@email.com")));
    }

    @DisplayName("given changed person object when partials update person then update person")
    @Test
    void partialsUpdatePerson() throws Exception {
        // When
        personRequest.setFirstName("any-firstname");

        ResultActions resultActions = mockMvc.perform(
                patch("/person/{person-id}", person.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(personRequest))
        );

        // Then
        resultActions.andDo(print()).andExpect(status().isNoContent());
    }
}
