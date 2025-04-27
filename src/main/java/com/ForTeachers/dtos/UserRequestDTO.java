package com.ForTeachers.dtos;

import com.ForTeachers.enums.UserType;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email,
        UserType userType,
        String password) {
}
