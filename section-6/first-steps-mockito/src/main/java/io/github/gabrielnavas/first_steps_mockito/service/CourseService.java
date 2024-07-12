package io.github.gabrielnavas.first_steps_mockito.service;

import java.util.List;

public interface CourseService {
    List<String> retrieveCourses(String student);

    void doSomething();

    void deleteCourse(String course);
}
