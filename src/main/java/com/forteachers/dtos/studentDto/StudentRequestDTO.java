package com.forteachers.dtos.studentDto;

import com.forteachers.enums.UserType;

public record StudentRequestDTO(
        String firstName,
        String lastName,
        String email,
        UserType userType,
        String password
) {
}
