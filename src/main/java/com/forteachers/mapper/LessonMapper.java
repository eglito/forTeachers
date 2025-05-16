package com.forteachers.mapper;

import com.forteachers.adapters.outputAdapters.LessonEntity;
import com.forteachers.dtos.lessonDto.LessonRequestDTO;
import com.forteachers.dtos.lessonDto.LessonResponseDTO;

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
