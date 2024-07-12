package io.github.gabrielnavas.first_steps_mockito.business;

import io.github.gabrielnavas.first_steps_mockito.service.CourseService;
import io.github.gabrielnavas.first_steps_mockito.service.stub.CourseServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseBusinessTest {

    @DisplayName("Test Courses Related To Spring When Using As Stub")
    @Test
    public void testCoursesRelatedToSpring_When_UsingAsStub() {
        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);
        String[] expected = new String[]{"Spring Course 1", "Spring Course 3", "Spring Course 5",};

        // When / Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("John");

        // Then / Assert
        Assertions.assertArrayEquals(
                filteredCourses.toArray(),
                expected
        );
    }

    @DisplayName("Test Courses Related To Spring When Using As Stub")
    @Test
    public void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {
        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);
        String[] expected = new String[]{};

        // When / Act
        var filteredCourses = business.retrieveCoursesRelatedToSpring("Foo Bar");

        // Then / Assert
        Assertions.assertArrayEquals(
                filteredCourses.toArray(),
                expected
        );
    }
}
