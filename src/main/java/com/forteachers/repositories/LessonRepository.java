package com.forteachers.repositories;

import com.forteachers.adapters.outputAdapters.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

}
