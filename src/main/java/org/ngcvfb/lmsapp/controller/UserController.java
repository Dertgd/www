package org.ngcvfb.lmsapp.controller;

import org.ngcvfb.lmsapp.model.CourseModel;
import org.ngcvfb.lmsapp.model.UserModel;
import org.ngcvfb.lmsapp.service.CourseService;
import org.ngcvfb.lmsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/{telegramId}")
    public ResponseEntity<UserModel> getUserByTelegramId(@PathVariable String telegramId) {
        return userService.findByTelegramId(telegramId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }


    // Получить курсы, которые пользователь создал
    @GetMapping("/{telegramId}/created-courses")
    public List<CourseModel> getUserCreatedCourses(@PathVariable String telegramId) {
        return userService.getUserCreatedCourses(telegramId);
    }

    // Получить курсы, которые пользователь прошел (на которые записан)
    @GetMapping("/{telegramId}/enrolled-courses")
    public List<CourseModel> getUserEnrolledCourses(@PathVariable String telegramId) {
        List<CourseModel> courses = userService.getUserEnrolledCourses(telegramId);
        return userService.getUserEnrolledCourses(telegramId);
    }
}
