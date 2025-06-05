package com.forteachers.dtos.teacherDto;

import com.forteachers.enums.UserType;

public record TeacherRequestDTO(
        String firstName,
        String lastName,
        String email,
        UserType userType,
        String password) {
}
