package com.ForTeachers.persistence;

import com.ForTeachers.dtos.lessonDto.LessonRequestDTO;
import com.ForTeachers.dtos.lessonDto.LessonResponseDTO;

public interface LessonPersistence {

    LessonResponseDTO save (LessonRequestDTO lessonRequestDTO, Long id);
    void delete (Long teacherId, Long lessonId);

}
