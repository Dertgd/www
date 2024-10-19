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
@RequestMapping("/api/themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping("/course/{courseId}")
    public List<ThemeModel> getAllThemesByCourseId(@PathVariable Long courseId) {
        return themeService.getAllThemesByCourseId(courseId);
    }

    @GetMapping("/{themeId}")
    public ResponseEntity<ThemeModel> getThemeById(@PathVariable Long themeId) {
        return themeService.getThemeById(themeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/course/{courseId}")
    public ThemeModel createTheme(@RequestBody ThemeModel theme, @PathVariable Long courseId) {
        return themeService.createTheme(theme, courseId);
    }

    @PutMapping("/{themeId}")
    public ResponseEntity<ThemeModel> updateTheme(@PathVariable Long themeId, @RequestBody ThemeModel theme) {
        return ResponseEntity.ok(themeService.updateTheme(themeId, theme));
    }

    @DeleteMapping("/{themeId}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long themeId) {
        themeService.deleteTheme(themeId);
        return ResponseEntity.noContent().build();
    }
}
