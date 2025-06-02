package com.forteachers.repositories;

import com.forteachers.adapters.outputAdapters.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
