package org.ngcvfb.lmsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private UserModel creator;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<UserModel> students;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<ThemeModel> themes;


    @ElementCollection
    private List<String> tags;

}
