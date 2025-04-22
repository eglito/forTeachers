package com.ForTeachers.dtos;

import com.ForTeachers.enums.UserType;
import org.apache.catalina.User;

public record CreateUserDTO(
        String firstName,
        String lastName,
        String email,
        UserType userType,
        String password) {
}
