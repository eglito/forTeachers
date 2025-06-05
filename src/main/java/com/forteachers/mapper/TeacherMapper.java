package com.forteachers.mapper;

import com.forteachers.adapters.outputAdapters.TeacherEntity;
import com.forteachers.adapters.outputAdapters.UserEntity;
import com.forteachers.dtos.teacherDto.TeacherRequestDTO;
import com.forteachers.dtos.teacherDto.TeacherResponseDTO;

public class TeacherMapper {

    public TeacherMapper() {
    }

    public TeacherEntity toEntity (TeacherRequestDTO dto){

        TeacherEntity entity = new TeacherEntity();
            entity.setFirstName (dto.firstName ());
            entity.setLastName (dto.lastName ());
            entity.setEmail (dto.email ());
            entity.setUserType (dto.userType ());

        return entity;
    }

    public TeacherResponseDTO toResponseDTO(UserEntity entity){

        TeacherResponseDTO responseDTO = new TeacherResponseDTO (
                entity.getFirstName (),
                entity.getEmail (),
                entity.getUserType ()
        );

        return responseDTO;

    }
}
