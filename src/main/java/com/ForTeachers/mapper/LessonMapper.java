package com.ForTeachers.mapper;

import com.ForTeachers.adapters.outputAdapters.LessonEntity;
import com.ForTeachers.dtos.lessonDto.LessonRequestDTO;
import com.ForTeachers.dtos.lessonDto.LessonResponseDTO;

public class LessonMapper {

    public LessonMapper() {

    }

    public LessonEntity toEntity(LessonRequestDTO dto){

        LessonEntity entity = new LessonEntity ();
        entity.setLessonTitle (dto.lessonTitle ());

        return entity;
    }

    public LessonResponseDTO toResponse (LessonEntity entity){
        return new LessonResponseDTO(entity.getLessonTitle ());

    }

}
