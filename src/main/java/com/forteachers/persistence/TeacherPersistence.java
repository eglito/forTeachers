package com.forteachers.persistence;

import com.forteachers.dtos.EmailResponseDTO;
import com.forteachers.dtos.teacherDto.TeacherRequestDTO;
import com.forteachers.dtos.teacherDto.TeacherResponseDTO;

import java.util.List;

public interface TeacherPersistence {

    TeacherResponseDTO save (TeacherRequestDTO teacherRequestDTO) throws IllegalAccessException;
    TeacherResponseDTO findById (Long id);
    void updateTeacher(TeacherRequestDTO teacherRequestDTO, Long id);
    List<EmailResponseDTO> getAllEmails();
    void deleteTeacher(Long id);
}
