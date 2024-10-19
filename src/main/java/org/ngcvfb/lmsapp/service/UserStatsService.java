package org.ngcvfb.lmsapp.service;

import org.ngcvfb.lmsapp.dto.UserStatsDTO;
import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.ThemeModel;
import org.ngcvfb.lmsapp.model.UserModel;
import org.ngcvfb.lmsapp.repository.CourseRepository;
import org.ngcvfb.lmsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStatsService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;


    public int getCompletedCoursesCount(String userId) {
        UserModel user = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getCompletedCourses().size();
    }

    // Получение полной статистики пользователя
    public UserStatsDTO getUserStatistics(String userId) {
        UserModel user = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Количество завершенных курсов
        int completedCoursesCount = user.getCompletedCourses().size();

        // Процент прогресса для всех курсов, на которые записан пользователь
        Map<String, Double> courseProgress = new HashMap<>();
        for (CourseModel course : user.getEnrolledCourses()) {
            double progress = getCourseCompletionPercentage(userId, course.getId());
            courseProgress.put(course.getName(), progress);
        }

        // Создаем DTO для передачи информации
        return new UserStatsDTO(completedCoursesCount, courseProgress);
    }

    // Вспомогательный метод для вычисления процента завершения курса
    public double getCourseCompletionPercentage(String userId, Long courseId) {
        UserModel user = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<ThemeModel> courseThemes = course.getThemes();
        long completedThemesCount = user.getCompletedThemes().stream()
                .filter(theme -> theme.getCourse().getId().equals(courseId))
                .count();

        if (courseThemes.isEmpty()) {
            return 0;
        }
        return ((double) completedThemesCount / courseThemes.size()) * 100;
    }
}
