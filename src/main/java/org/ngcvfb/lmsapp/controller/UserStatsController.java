package org.ngcvfb.lmsapp.controller;

import org.ngcvfb.lmsapp.dto.UserStatsDTO;
import org.ngcvfb.lmsapp.service.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserStatsController {
    @Autowired
    private UserStatsService userStatisticsService;

    @GetMapping("/{userId}/statistics")
    public ResponseEntity<UserStatsDTO> getUserStatistics(@PathVariable String userId) {
        UserStatsDTO statistics = userStatisticsService.getUserStatistics(userId);
        return ResponseEntity.ok(statistics);
    }

}
