package com.forteachers.adapters.inputAdapters;

import com.forteachers.dtos.studentDto.StudentRequestDTO;
import com.forteachers.dtos.studentDto.StudentResponseDTO;
import com.forteachers.services.StudentService;
import com.forteachers.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/{teacherId}/classroom/{classroomId}")
    public String generationToken(@PathVariable Long teacherId, @PathVariable Long classroomId){
        return teacherService.generateInvitationToken (teacherId, classroomId);
    }

    @PostMapping //register?token=abc123
    public ResponseEntity<StudentResponseDTO> registerStudent (@RequestParam("token") String token, @RequestBody StudentRequestDTO studentRequestDTO) throws IllegalAccessException {
        return ResponseEntity.ok (studentService.registerStudents (token, studentRequestDTO));
    }

}
