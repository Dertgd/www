package org.ngcvfb.lmsapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "themes")
public class ThemeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob  // Если это будет большой текст
    private String content;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;
}
