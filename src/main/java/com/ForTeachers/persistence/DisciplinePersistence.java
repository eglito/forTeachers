package com.ForTeachers.persistence;

import com.ForTeachers.dtos.disciplineDto.DisciplineRequestDTO;
import com.ForTeachers.dtos.disciplineDto.DisciplineResponseDTO;

public interface DisciplinePersistence {

    DisciplineResponseDTO save (DisciplineRequestDTO requestDTO, Long id);
    void delete(Long teacherId, Long disciplineId);

}
