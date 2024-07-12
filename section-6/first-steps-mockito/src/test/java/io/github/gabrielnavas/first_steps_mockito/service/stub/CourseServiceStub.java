package io.github.gabrielnavas.first_steps_mockito.service.stub;

import io.github.gabrielnavas.first_steps_mockito.service.CourseService;

import java.util.Arrays;
import java.util.List;

public class CourseServiceStub implements CourseService {
    @Override
    public List<String> retrieveCourses(String student) {
        return Arrays.asList(
                "Spring Course 1",
                "Course 2",
                "Spring Course 3",
                "Course 4",
                "Spring Course 5",
                "Course 6"
        );
    }

    @Override
    public void doSomething() {

    }

    @Override
    public void deleteCourse(String course) {

    }
}
