package com.ForTeachers.adapters.inputAdapters;

import com.ForTeachers.dtos.EmailResponseDTO;
import com.ForTeachers.dtos.userDto.UserRequestDTO;
import com.ForTeachers.dtos.userDto.UserResponseDTO;
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
public class TeacherController {

    @Autowired
    private TeacherService service;

    @PostMapping("/create")
    public ResponseEntity<Void> save(@RequestBody UserRequestDTO userRequestDTO){
        service.save (userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById (id));
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
        return ResponseEntity.ok(service.getAllEmails());
    }

}
