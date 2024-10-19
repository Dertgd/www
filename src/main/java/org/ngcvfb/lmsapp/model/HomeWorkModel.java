package org.ngcvfb.lmsapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private CourseModel course;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    @JsonIgnore
    private ThemeModel theme;

    @Column(columnDefinition="TEXT")
    private String question;

    @Column(columnDefinition="TEXT")
    private String answer;

    @Column(columnDefinition="TEXT")
    private String solution;

}
