package org.ngcvfb.lmsapp.controller;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.ThemeModel;
import org.ngcvfb.lmsapp.service.CourseService;
import org.ngcvfb.lmsapp.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ThemeService themeService;

    @GetMapping
    public List<CourseModel> getAllCourses() {
        return courseService.getAllCourses();
    }
    @GetMapping("/search")
    public ResponseEntity<List<CourseModel>> searchCoursesByTag(@RequestParam String tag) {
        List<CourseModel> courses = courseService.findCoursesByTag(tag);
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping("/create")
    public CourseModel createCourse(@RequestBody CourseModel course, @RequestParam String instructorId) {
        return courseService.createCourse(course, instructorId);
    }


    @PutMapping("/{courseId}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable Long courseId, @RequestBody CourseModel course) {
        courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(course);
    }
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<String> enrollUserToCourse(
            @PathVariable Long courseId,
            @RequestParam String userId) {

        courseService.enrollStudent(courseId, userId);
        return ResponseEntity.ok("User successfully enrolled to the course");
    }




}
