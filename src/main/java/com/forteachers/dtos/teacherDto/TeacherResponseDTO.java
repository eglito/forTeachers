package com.forteachers.dtos.teacherDto;

import com.forteachers.enums.UserType;

public record TeacherResponseDTO(String firstName,
                                 String email,
                                 UserType userType) {
}
