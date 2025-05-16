package com.ForTeachers.mapper;

import com.ForTeachers.adapters.outputAdapters.TeacherEntity;
import com.ForTeachers.adapters.outputAdapters.UserEntity;
import com.ForTeachers.dtos.userDto.UserRequestDTO;
import com.ForTeachers.dtos.userDto.UserResponseDTO;

public class UserMapper {

    public UserMapper() {
    }

    public TeacherEntity toEntity (UserRequestDTO dto){

        TeacherEntity entity = new TeacherEntity();
            entity.setFirstName (dto.firstName ());
            entity.setLastName (dto.lastName ());
            entity.setEmail (dto.email ());
            entity.setUserType (dto.userType ());
            entity.setPassword (dto.password ());

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
