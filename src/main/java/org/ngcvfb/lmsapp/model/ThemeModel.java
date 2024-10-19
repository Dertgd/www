package org.ngcvfb.lmsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "themes")
public class ThemeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private CourseModel course;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HomeWorkModel> homeWorks;


}
