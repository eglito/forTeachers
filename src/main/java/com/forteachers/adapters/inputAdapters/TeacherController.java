package com.forteachers.adapters.inputAdapters;

import com.forteachers.dtos.EmailResponseDTO;
import com.forteachers.dtos.teacherDto.TeacherRequestDTO;
import com.forteachers.dtos.teacherDto.TeacherResponseDTO;
import com.forteachers.services.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "tag_at_class_teacher", description = "Teacher tags")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @Tag(name = "create teacher user")
    @Tag(name = "common_tag_at_methods_level")
    @Tag(name = "createTeacher")
    @PostMapping("/create")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true)
    public ResponseEntity<TeacherResponseDTO> save(@RequestBody TeacherRequestDTO teacherRequestDTO) throws IllegalAccessException {
        service.save (teacherRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TeacherResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById (id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO, @PathVariable Long id){
        service.updateTeacher(teacherRequestDTO, id);
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
