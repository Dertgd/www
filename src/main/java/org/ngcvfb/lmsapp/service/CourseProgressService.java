package org.ngcvfb.lmsapp.service;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.ThemeModel;
import org.ngcvfb.lmsapp.model.UserModel;
import org.ngcvfb.lmsapp.repository.CourseRepository;
import org.ngcvfb.lmsapp.repository.ThemeRepository;
import org.ngcvfb.lmsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseProgressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ThemeRepository themeRepository;


    public void completeTheme(String userId, Long themeId) {
        UserModel user = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ThemeModel theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));

        // Проверяем, что пользователь записан на курс этой темы
        if (!user.getEnrolledCourses().contains(theme.getCourse())) {
            throw new RuntimeException("User is not enrolled in this course");
        }

        // Проверяем, что тема еще не завершена
        if (user.getCompletedThemes().contains(theme)) {
            throw new RuntimeException("Theme already completed");
        }

        // Добавляем тему в список завершенных
        user.getCompletedThemes().add(theme);
        userRepository.save(user);
    }

    public double getCourseCompletionPercentage(String userId, Long courseId) {
        UserModel user = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Получаем все темы курса
        List<ThemeModel> courseThemes = course.getThemes();
        long completedThemesCount = user.getCompletedThemes().stream()
                .filter(theme -> theme.getCourse().getId().equals(courseId))
                .count();

        // Рассчитываем процент завершения
        if (courseThemes.isEmpty()) {
            return 0;
        }
        return ((double) completedThemesCount / courseThemes.size()) * 100;
    }
}
