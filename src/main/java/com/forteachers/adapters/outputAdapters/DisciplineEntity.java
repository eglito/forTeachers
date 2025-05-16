package com.forteachers.adapters.outputAdapters;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id")
    private TeacherEntity teacher;

    @OneToOne
    private ClassroomEntity classroom;

    @OneToMany (mappedBy = "discipline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LessonEntity> lessonList;

    @ManyToMany
    @JoinTable(
            name = "discipline_student",
            joinColumns = @JoinColumn(name = "discipline_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<StudentEntity> studentsList;

    public void addStudent(StudentEntity entity){
        studentsList.add(entity);
    }

    public void removeStudent(StudentEntity student){
        studentsList.remove(student);
    }

    public void addLesson(LessonEntity lesson){
        lessonList.add(lesson);
    }

    public void removeLesson(LessonEntity lesson){
        lessonList.remove(lesson);
    }
}
