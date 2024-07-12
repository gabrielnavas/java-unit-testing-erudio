package io.github.gabrielnavas.first_steps_mockito.business;

import io.github.gabrielnavas.first_steps_mockito.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CourseBusinessMockWithBDDTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setUp() {
        // Given / Arrange
        mockService = Mockito.mock(CourseService.class);
        business = new CourseBusiness(mockService);
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

    @DisplayName("Delete Course not Related to Spring Using Mockito should call Method")
    @Test
    public void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_ShouldCallMethodDeleteCourse() {
        // Given
        given(mockService.retrieveCourses("John")).willReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("John");

        // Then
        verify(mockService,
                never()
        ).deleteCourse("Spring Course"); // courses "Srping Course" is never called
        verify(
                mockService,
                times(2)
        ).deleteCourse("Course"); // course with "Course" is called 2 times
    }


    @DisplayName("Delete Course not Related to Spring Using Mockito should call Method At least")
    @Test
    public void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_ShouldCallMethodDeleteCourseAtLeast() {
        // Given
        given(mockService.retrieveCourses("John")).willReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("John");

        // Then
        verify(
                mockService,
                atLeast(1)
        ).deleteCourse("Course"); // course with "Course" is called 2 times
    }
}
