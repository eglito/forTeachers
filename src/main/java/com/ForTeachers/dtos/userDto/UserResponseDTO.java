package com.ForTeachers.dtos.userDto;

import com.ForTeachers.enums.UserType;

public record UserResponseDTO(String firstName,
                              String email,
                              UserType userType) {
}
