package com.forteachers.mapper;

import com.forteachers.adapters.outputAdapters.StudentEntity;
import com.forteachers.adapters.outputAdapters.UserEntity;
import com.forteachers.dtos.userDto.UserRequestDTO;
import com.forteachers.dtos.userDto.UserResponseDTO;

public class StudentMapper {

    public StudentMapper() {
    }

    public StudentEntity toEntity (UserRequestDTO dto){

        StudentEntity entity = new StudentEntity();
        entity.setFirstName (dto.firstName ());
        entity.setLastName (dto.lastName ());
        entity.setEmail (dto.email ());
        entity.setUserType (dto.userType ());

        return entity;
    }

    public UserResponseDTO toResponseDTO(UserEntity entity){

        UserResponseDTO responseDTO = new UserResponseDTO (
                entity.getFirstName (),
                entity.getEmail (),
                entity.getUserType ()
        );

        return responseDTO;

    }
}
