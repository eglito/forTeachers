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

    @ManyToMany(mappedBy = "studentsList")
    private Set<DisciplineEntity> disciplineList;

    @ManyToMany(mappedBy = "studentsList")
    private Set<ClassroomEntity> classroomList;

    @ManyToMany(mappedBy = "studentsList")
    private Set<LessonEntity> lessonList;

}
