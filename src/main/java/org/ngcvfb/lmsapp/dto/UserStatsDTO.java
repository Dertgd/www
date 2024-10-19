package org.ngcvfb.lmsapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UserStatsDTO {
    private int completedCoursesCount;
    private Map<String, Double> courseProgress;

    public UserStatsDTO(int completedCoursesCount, Map<String, Double> courseProgress) {
        this.completedCoursesCount = completedCoursesCount;
        this.courseProgress = courseProgress;
    }
}
