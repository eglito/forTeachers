package com.ForTeachers.dtos;

import com.ForTeachers.enums.UserType;

public record UserResponseDTO(String firstName,
                              String email,
                              UserType userType) {
}
