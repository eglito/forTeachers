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

    @ManyToMany(mappedBy = "listStudent")
    private Set<LessonEntity> lessonList;

    public void addDiscipline(DisciplineEntity discipline){
        disciplineList.add (discipline);
        discipline.addStudent (this);
    }

    public void removeDiscipline(DisciplineEntity discipline){
        disciplineList.remove (discipline);
        discipline.removeStudent (this);
    }

    public void addClassroom(ClassroomEntity classroom){
        classroomList.add (classroom);
        classroom.addStudent (this);
    }

    public void removeClassroom(ClassroomEntity classroom){
        classroomList.remove (classroom);
        classroom.removeStudent (this);
    }

    public void addLesson(LessonEntity lesson){
        lessonList.add(lesson);
        lesson.addStudent(this);
    }

    public void removeLesson(LessonEntity lesson){
        lessonList.remove (lesson);
        lesson.removeStudent(this);
    }

}
