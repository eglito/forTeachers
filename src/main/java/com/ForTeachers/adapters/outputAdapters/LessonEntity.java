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
    private String lessonTitle;
    private String activity;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private DisciplineEntity discipline;

    @ManyToMany
    @JoinTable( name = "lesson_students",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<StudentEntity> listStudent;

    public void addStudent(StudentEntity student){
        listStudent.add(student);
        student.addLesson(this);
    }

    public void removeStudent(StudentEntity student){
        listStudent.remove (student);
        student.removeLesson(this);
    }
}
