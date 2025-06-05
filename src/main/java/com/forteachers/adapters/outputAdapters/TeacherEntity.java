package com.forteachers.adapters.outputAdapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers")
public class TeacherEntity extends UserEntity{

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentEntity> listStudent;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DisciplineEntity> listDisciplines;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassroomEntity> listClassroom;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LessonEntity> listLesson;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvitationToken> listToken;


    public void addStudent(StudentEntity student){
        listStudent.add(student);
        student.setTeacher (this);
    }

    public void removeStudent(StudentEntity student){
        listStudent.remove (student);
        student.setTeacher(null);
    }

    public void addDisciplines(DisciplineEntity discipline){
        listDisciplines.add (discipline);
        discipline.setTeacher (this);
    }

    public void removeDisciplines (DisciplineEntity discipline){
        listDisciplines.remove (discipline);
        discipline.setTeacher(null);
    }

    public void addClassroom(ClassroomEntity classroom){
        listClassroom.add (classroom);
        classroom.setTeacher (this);
    }

    public void removeClassroom(ClassroomEntity classroom){
        listClassroom.remove (classroom);
        classroom.setTeacher (null);
    }

    public void addLesson(LessonEntity lesson){
        listLesson.add (lesson);
        lesson.setTeacher (this);
    }

    public void removeLesson(LessonEntity lesson){
        listLesson.remove (lesson);
        lesson.setTeacher (null);
    }

}
