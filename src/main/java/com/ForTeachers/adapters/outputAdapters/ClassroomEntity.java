package com.ForTeachers.adapters.outputAdapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name = "classrooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @OneToOne
    private DisciplineEntity discipline;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
    @ManyToMany (mappedBy = "classroomList")
    private Set<StudentEntity> studentList;

}
