package com.ForTeachers.adapters.outputAdapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String classTitle;
    private String activity;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private DisciplineEntity discipline;
    @ManyToMany(mappedBy = "lessonList")
    private Set<StudentEntity> studentsList;
}
