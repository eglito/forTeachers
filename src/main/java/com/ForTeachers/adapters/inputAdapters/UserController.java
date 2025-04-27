package com.ForTeachers.adapters.inputAdapters;

import com.ForTeachers.dtos.EmailResponseDTO;
import com.ForTeachers.dtos.UserRequestDTO;
import com.ForTeachers.dtos.UserResponseDTO;
import com.ForTeachers.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class UserController {

    @Autowired
    private TeacherService service;

    @PostMapping("/new_user")
    public ResponseEntity<Void> createTeacher(@RequestBody UserRequestDTO userRequestDTO){
        service.creatTeacher(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getTeacherForId(@PathVariable Long id){
        return ResponseEntity.ok(service.getUser(id).getBody ());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateTeacher(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id){
        service.updateTeacher(userRequestDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        service.deleteTeacher(id);
        return ResponseEntity.ok().build ();
    }

    @GetMapping("/emails")
    public ResponseEntity<List<EmailResponseDTO>> getAllTeachersEmails(){
        return ResponseEntity.ok(service.getAllEmails().getBody());
    }

}
