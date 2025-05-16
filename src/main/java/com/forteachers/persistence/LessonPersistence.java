package com.forteachers.persistence;

import com.forteachers.dtos.lessonDto.LessonRequestDTO;
import com.forteachers.dtos.lessonDto.LessonResponseDTO;

public interface LessonPersistence {

    LessonResponseDTO save (LessonRequestDTO lessonRequestDTO, Long id);
    void delete (Long teacherId, Long lessonId);

}
