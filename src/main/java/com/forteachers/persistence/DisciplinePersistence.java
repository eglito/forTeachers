package com.forteachers.persistence;

import com.forteachers.dtos.disciplineDto.DisciplineRequestDTO;
import com.forteachers.dtos.disciplineDto.DisciplineResponseDTO;

public interface DisciplinePersistence {

    DisciplineResponseDTO save (DisciplineRequestDTO requestDTO, Long id);
    void delete(Long teacherId, Long disciplineId);

}
