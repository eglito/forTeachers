package com.ForTeachers.repositorios;

import com.ForTeachers.adapters.outputAdapters.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {

}
