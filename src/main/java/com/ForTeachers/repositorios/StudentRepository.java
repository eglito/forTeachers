package com.ForTeachers.repositorios;

import com.ForTeachers.adapters.outputAdapters.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
