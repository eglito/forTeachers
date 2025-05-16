package com.ForTeachers.persistence;

import com.ForTeachers.dtos.EmailResponseDTO;
import com.ForTeachers.dtos.userDto.UserRequestDTO;
import com.ForTeachers.dtos.userDto.UserResponseDTO;

import java.util.List;

public interface TeacherPersistence {

    void save (UserRequestDTO userRequestDTO);
    UserResponseDTO findById (Long id);
    void updateTeacher(UserRequestDTO userRequestDTO, Long id);
    List<EmailResponseDTO> getAllEmails();
    void deleteTeacher(Long id);
}
