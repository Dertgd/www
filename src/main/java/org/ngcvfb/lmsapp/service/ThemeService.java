package org.ngcvfb.lmsapp.service;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.ThemeModel;
import org.ngcvfb.lmsapp.repository.CourseRepository;
import org.ngcvfb.lmsapp.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<ThemeModel> getAllThemesByCourseId(Long courseId) {
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return course.getThemes(); // если связь ManyToOne не обновлена - использовать запросы
    }

    // Получить тему по ID
    public Optional<ThemeModel> getThemeById(Long id) {
        return themeRepository.findById(id);
    }

    // Создать тему для конкретного курса
    public ThemeModel createTheme(ThemeModel theme, Long courseId) {
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        theme.setCourse(course);
        return themeRepository.save(theme);
    }

    // Обновить тему
    public ThemeModel updateTheme(Long themeId, ThemeModel updatedTheme) {
        ThemeModel theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
        theme.setTitle(updatedTheme.getTitle());
        theme.setDescription(updatedTheme.getDescription());
        return themeRepository.save(theme);
    }

    // Удалить тему
    public void deleteTheme(Long themeId) {
        themeRepository.deleteById(themeId);
    }



}
