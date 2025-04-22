package com.ForTeachers.adapters.outputAdapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "disciplines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private TeacherEntity teacher;
    @OneToMany (mappedBy = "discipline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LessonEntity> listLesson;
    @ManyToMany(mappedBy = "disciplineList")
    private Set<StudentEntity> studentsList;
}
