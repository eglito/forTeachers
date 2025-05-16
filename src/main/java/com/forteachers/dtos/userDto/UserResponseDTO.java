package com.forteachers.dtos.userDto;

import com.forteachers.enums.UserType;

public record UserResponseDTO(String firstName,
                              String email,
                              UserType userType) {
}
