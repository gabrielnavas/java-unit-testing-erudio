package io.github.gabrielnavas.first_steps_mockito.business;

import io.github.gabrielnavas.first_steps_mockito.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class CourseBusinessMockTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setUp() {
        // Given / Arrange
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "Spring Course 1",
                "Course 2",
                "Spring Course 3",
                "Course 4",
                "Spring Course 5",
                "Course 6"
        );
    }

    @DisplayName("Test Courses Related To Spring When Using As Stub")
    @Test
    public void testCoursesRelatedToSpring_When_UsingAMock() {
        // Given / Arrange
        when(mockService.retrieveCourses("John")).thenReturn(courses);
        String[] expected = new String[]{
                "Spring Course 1",
                "Spring Course 3",
                "Spring Course 5",
        };

        // When / Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("John");

        // Then / Assert
        Assertions.assertArrayEquals(
                filteredCourses.toArray(),
                expected
        );
    }

    @DisplayName("Delete Course not Related to Spring Using Mockito should call Method V2 (prefer this)")
    @Test
    public void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_ShouldCallMethodDeleteCourse() {
        // Given
        when(mockService.retrieveCourses("John")).thenReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("John");

        // Then
        verify(mockService, never()).deleteCourse(courses.get(0)); // courses index 0 is never called
        verify(mockService).deleteCourse(courses.get(1)); // course index 1 is called
    }

    @DisplayName("Delete Course not Related to Spring Using Mockito should call Method With V2")
    @Test
    public void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_ShouldCallMethodDeleteCourseV2() {
        // Given
        when(mockService.retrieveCourses("John")).thenReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("John");

        // Then
        then(mockService).should(never()).deleteCourse(courses.get(0)); // courses index 0 is never called
        then(mockService).should().deleteCourse(courses.get(1)); // course index 1 is called
    }
}
