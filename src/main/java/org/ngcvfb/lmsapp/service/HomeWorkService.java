package org.ngcvfb.lmsapp.service;

import org.ngcvfb.lmsapp.model.HomeWorkModel;
import org.ngcvfb.lmsapp.model.ThemeModel;
import org.ngcvfb.lmsapp.repository.HomeWorkRepository;
import org.ngcvfb.lmsapp.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeWorkService {

    @Autowired
    private HomeWorkRepository homeWorkRepository;

    @Autowired
    private ThemeRepository themeRepository;

    // Получить все домашние задания для конкретной темы
    public List<HomeWorkModel> getAllHomeWorksByThemeId(Long themeId) {
        ThemeModel theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
        return theme.getHomeWorks(); // если связь ManyToOne не обновлена - использовать запросы
    }

    // Получить домашнюю работу по ID
    public Optional<HomeWorkModel> getHomeWorkById(Long id) {
        return homeWorkRepository.findById(id);
    }

    // Создать домашнюю работу для конкретной темы
    public HomeWorkModel createHomeWork(HomeWorkModel homework, Long themeId) {
        ThemeModel theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
        homework.setTheme(theme);
        return homeWorkRepository.save(homework);
    }

    // Обновить домашнюю работу
    public HomeWorkModel updateHomeWork(Long homeworkId, HomeWorkModel updatedHomework) {
        HomeWorkModel homework = homeWorkRepository.findById(homeworkId)
                .orElseThrow(() -> new RuntimeException("Homework not found"));
        homework.setQuestion(updatedHomework.getQuestion());
        homework.setSolution(updatedHomework.getSolution());
        return homeWorkRepository.save(homework);
    }

    // Удалить домашнюю работу
    public void deleteHomeWork(Long homeworkId) {
        homeWorkRepository.deleteById(homeworkId);
    }
}

