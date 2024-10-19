package org.ngcvfb.lmsapp.controller;

import org.ngcvfb.lmsapp.service.CourseProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseProgressController {
    @Autowired
    private CourseProgressService courseProgressService;

    @PostMapping("/theme/{themeId}/complete")
    public ResponseEntity<String> completeTheme(
            @PathVariable Long themeId,
            @RequestParam String userId) {

        courseProgressService.completeTheme(userId, themeId);
        return ResponseEntity.ok("Theme successfully completed");
    }

    @GetMapping("/{courseId}/progress")
    public ResponseEntity<Double> getCourseCompletionPercentage(
            @PathVariable Long courseId,
            @RequestParam String userId) {

        double percentage = courseProgressService.getCourseCompletionPercentage(userId, courseId);
        return ResponseEntity.ok(percentage);
    }
}
