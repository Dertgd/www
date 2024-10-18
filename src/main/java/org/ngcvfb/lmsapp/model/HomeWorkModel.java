package org.ngcvfb.lmsapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "homework")
public class HomeWorkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private ThemeModel theme;

    @Lob
    private String question;

    @Lob
    private String answer;

    @Lob
    private String solution;

}
