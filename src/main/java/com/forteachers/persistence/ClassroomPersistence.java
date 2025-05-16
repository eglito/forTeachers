package com.forteachers.persistence;

import com.forteachers.dtos.classroomDto.ClassroomRequestDTO;
import com.forteachers.dtos.classroomDto.ClassroomResponseDTO;

public interface ClassroomPersistence {

    ClassroomResponseDTO save (ClassroomRequestDTO requestDTO, Long id);
    void delete (Long teacherId, Long classroomId);

}
