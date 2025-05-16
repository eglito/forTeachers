package com.forteachers.adapters.inputAdapters;

import com.forteachers.dtos.classroomDto.ClassroomRequestDTO;
import com.forteachers.dtos.classroomDto.ClassroomResponseDTO;
import com.forteachers.services.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/{id}")
    public ResponseEntity<ClassroomResponseDTO> save(@RequestBody ClassroomRequestDTO requestDTO, @PathVariable Long id){
        return ResponseEntity.ok (classroomService.save (requestDTO, id));
    }

    @DeleteMapping("{teacherId}/{classroomId}")
    public void delete(@PathVariable Long teacherId,@PathVariable Long classroomId){
        classroomService.delete (teacherId, classroomId);
    }
}
