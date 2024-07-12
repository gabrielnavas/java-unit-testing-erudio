package io.github.gabrielnavas.mockito_advanced.service;

import java.util.List;

public interface CourseService {
    List<String> retrieveCourses(String student);

    void doSomething();

    void deleteCourse(String course);
}
