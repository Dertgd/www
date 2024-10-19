package org.ngcvfb.lmsapp.service;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.UserModel;
import org.ngcvfb.lmsapp.repository.CourseRepository;
import org.ngcvfb.lmsapp.repository.ThemeRepository;
import org.ngcvfb.lmsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public List<CourseModel> getAllCourses() {
        return courseRepository.findAll();
    }
    public Optional<CourseModel> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<CourseModel> findCoursesByTag(String tag) {
        return courseRepository.findByTagsContaining(tag);
    }

    public CourseModel createCourse(CourseModel course, String instructorId) {
        UserModel creator = userRepository.findByTelegramId(instructorId).orElseThrow(() -> new RuntimeException("User not found"));
        course.setCreator(creator);
        return courseRepository.save(course);
    }

    public CourseModel updateCourse(Long courseId, CourseModel updatedCourseData) {
        Optional<CourseModel> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            CourseModel course = courseOpt.get();
            course.setName(updatedCourseData.getName());
            course.setDescription(updatedCourseData.getDescription());
            return courseRepository.save(course);
        }
        throw new RuntimeException("Course not found");
    }

    public void enrollStudent(Long courseId, String studentId) {
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        UserModel student = userRepository.findByTelegramId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (student.getEnrolledCourses().contains(course)){
            throw new RuntimeException("Student already enrolled");
        }
        course.getStudents().add(student);
        student.getEnrolledCourses().add(course);
        userRepository.save(student);
        courseRepository.save(course);
    }

}
