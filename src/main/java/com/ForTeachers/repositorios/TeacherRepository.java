package com.ForTeachers.repositorios;

import com.ForTeachers.adapters.outputAdapters.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
