package com.ForTeachers.persistence;

import com.ForTeachers.dtos.classroomDTO.ClassroomRequestDTO;
import com.ForTeachers.dtos.classroomDTO.ClassroomResponseDTO;

public interface ClassroomPersistence {

    ClassroomResponseDTO save (ClassroomRequestDTO requestDTO, Long id);
    void delete (Long teacherId, Long classroomId);

}
