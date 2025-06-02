package com.forteachers.persistence;

import com.forteachers.dtos.EmailResponseDTO;
import com.forteachers.dtos.userDto.UserRequestDTO;
import com.forteachers.dtos.userDto.UserResponseDTO;

import java.util.List;

public interface TeacherPersistence {

    UserResponseDTO save (UserRequestDTO userRequestDTO) throws IllegalAccessException;
    UserResponseDTO findById (Long id);
    void updateTeacher(UserRequestDTO userRequestDTO, Long id);
    List<EmailResponseDTO> getAllEmails();
    void deleteTeacher(Long id);
}
