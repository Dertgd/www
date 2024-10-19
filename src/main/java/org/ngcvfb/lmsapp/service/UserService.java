package org.ngcvfb.lmsapp.service;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.UserModel;
import org.ngcvfb.lmsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> findByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public List<CourseModel> getUserCreatedCourses(String userId) {
        UserModel userModel = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userModel.getCreatedCourses();
    }

    public List<CourseModel> getUserEnrolledCourses(String userId) {
        UserModel user = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEnrolledCourses();
    }



}
