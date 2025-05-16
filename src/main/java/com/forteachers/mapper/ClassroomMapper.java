package com.forteachers.mapper;

import com.forteachers.adapters.outputAdapters.ClassroomEntity;
import com.forteachers.dtos.classroomDto.ClassroomRequestDTO;
import com.forteachers.dtos.classroomDto.ClassroomResponseDTO;

public class ClassroomMapper {

    public ClassroomMapper(){

    }

    public ClassroomEntity toEntity (ClassroomRequestDTO requestDTO){

        ClassroomEntity entity = new ClassroomEntity ();
        entity.setName (requestDTO.name ());
        return entity;

    }

    public ClassroomResponseDTO toResponse (ClassroomEntity entity){

        ClassroomResponseDTO responseDTO = new ClassroomResponseDTO(
                entity.getName (),
                entity.getTeacher ().getFirstName (),
                entity.getTeacher ().getFirstName (),
                entity.getTeacher ().getEmail ()
        );
        return responseDTO;
    }
}
