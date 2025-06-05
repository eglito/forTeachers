package com.forteachers.dtos.studentDto;

import com.forteachers.enums.UserType;

public record StudentResponseDTO(String firstName,
                                 String email,
                                 UserType userType) {
}
