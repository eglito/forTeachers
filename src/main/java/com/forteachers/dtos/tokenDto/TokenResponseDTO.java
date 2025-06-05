package com.forteachers.dtos.tokenDto;

import com.forteachers.adapters.outputAdapters.ClassroomEntity;
import com.forteachers.adapters.outputAdapters.TeacherEntity;

public record TokenResponseDTO(
        TeacherEntity teacher,
        ClassroomEntity classroom
) {
}
