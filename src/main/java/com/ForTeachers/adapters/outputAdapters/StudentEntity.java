package com.ForTeachers.adapters.outputAdapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity extends UserEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
    @ManyToMany
    @JoinTable(
            name = "student_disciplines",
            joinColumns =  @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<DisciplineEntity> disciplineList;
    @ManyToMany
    @JoinTable(
            name = "student_classrooms",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private Set<ClassroomEntity> classroomList;
    @ManyToMany
    @JoinTable(
            name = "student_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private Set<LessonEntity> lessonList;

}
