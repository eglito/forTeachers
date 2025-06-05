package com.forteachers.mapper;

import com.forteachers.adapters.outputAdapters.StudentEntity;
import com.forteachers.adapters.outputAdapters.UserEntity;
import com.forteachers.dtos.studentDto.StudentRequestDTO;
import com.forteachers.dtos.studentDto.StudentResponseDTO;

public class StudentMapper {

    public StudentMapper() {
    }

    public StudentEntity toEntity (StudentRequestDTO dto){

        StudentEntity entity = new StudentEntity();
        entity.setFirstName (dto.firstName ());
        entity.setLastName (dto.lastName ());
        entity.setEmail (dto.email ());
        entity.setUserType (dto.userType ());

        return entity;
    }

    public StudentResponseDTO toResponseDTO(UserEntity entity){

        StudentResponseDTO responseDTO = new StudentResponseDTO (
                entity.getFirstName (),
                entity.getEmail (),
                entity.getUserType ()
        );

        return responseDTO;

    }
}
