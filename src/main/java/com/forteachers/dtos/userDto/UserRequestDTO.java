package com.forteachers.dtos.userDto;

import com.forteachers.enums.UserType;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email,
        UserType userType,
        String password) {
}
