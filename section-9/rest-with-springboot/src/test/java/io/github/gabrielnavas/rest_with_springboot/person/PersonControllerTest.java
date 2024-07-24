package io.github.gabrielnavas.rest_with_springboot.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    void setup() {
        personRequest = new PersonRequest("John", "Taylor", "john@email.com", "1 Street", "Male");
        person = new Person(1L, personRequest.getFirstName(), personRequest.getLastName(), personRequest.getEmail(), personRequest.getAddress(), personRequest.getGender());
    }

    @DisplayName("")
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
}
