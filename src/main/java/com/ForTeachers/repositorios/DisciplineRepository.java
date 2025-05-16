package com.ForTeachers.repositorios;

import com.ForTeachers.adapters.outputAdapters.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Long> {
}
