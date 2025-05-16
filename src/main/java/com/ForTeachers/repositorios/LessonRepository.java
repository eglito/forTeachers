package com.ForTeachers.repositorios;

import com.ForTeachers.adapters.outputAdapters.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

}
