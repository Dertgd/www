package org.ngcvfb.lmsapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private UserModel creator;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<ThemeModel> themes;
}
