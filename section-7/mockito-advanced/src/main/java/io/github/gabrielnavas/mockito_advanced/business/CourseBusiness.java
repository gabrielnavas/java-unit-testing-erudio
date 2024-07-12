package io.github.gabrielnavas.mockito_advanced.business;


import io.github.gabrielnavas.mockito_advanced.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// SUT - System (Method) Under Test
public class CourseBusiness {

    // CourseService is a dependency
    private CourseService courseService;

    public CourseBusiness(CourseService service) {
        this.courseService = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {
        List<String> filteredCourses = new ArrayList<>();
        if (student.equals("Foo Bar")) {
            return filteredCourses;
        }

        var allCourses = courseService.retrieveCourses(student);
        for (var course : allCourses) {
            if (course.contains("Spring")) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    public void deleteCoursesNotRelatedToSpring(String student) {
        var allCourses = courseService.retrieveCourses(student);
        for (var course : allCourses) {
            if (!course.contains("Spring")) {
                courseService.deleteCourse(course);
            }
        }
    }
}
