package io.github.gabrielnavas.mockito_advanced.mockito;

import io.github.gabrielnavas.mockito_advanced.business.CourseBusiness;
import io.github.gabrielnavas.mockito_advanced.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // to use @Mock
public class MockitoInjectMocksTest {

    @Mock // create an instance of the mock
    CourseService mockService;

    @InjectMocks // inject the course service mock
    CourseBusiness business;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    List<String> courses;

    @BeforeEach
    void setUp() {
        // Given / Arrange
        courses = Arrays.asList(
                "Spring Course",
                "Course",
                "Course"
        );
    }

    @DisplayName("Test Courses Related To Spring When Using As Stub")
    @Test
    public void testCoursesRelatedToSpring_When_UsingAMock() {
        // Given / Arrange
        given(mockService.retrieveCourses("John")).willReturn(courses);
        String[] expected = new String[]{"Spring Course"};

        // When / Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("John");

        // Then / Assert
        assertThat(expected, is(filteredCourses.toArray()));
    }

    @DisplayName("Delete Courses Not Related To Spring Capturing Arguments Should Call Method Delete Course")
    @Test
    public void testDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_DeleteCourse() {
        // Given
        courses = new ArrayList<>() {{
            add("Course 1");
            add("Course 1");
            add("Spring Course 2");
        }};
        List<String> expected = new ArrayList<>() {{
            add("Course 1");
            add("Course 1");
        }};

        when(mockService.retrieveCourses("John")).thenReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("John");

        // Then
        verify(mockService, times(2)).deleteCourse(argumentCaptor.capture()); // call method 2 times with argument
        assertEquals(expected, argumentCaptor.getAllValues()); // verify if argument is courses.get(0)
    }
}
