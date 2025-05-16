package com.forteachers.adapters.inputAdapters;

import com.forteachers.dtos.lessonDto.LessonRequestDTO;
import com.forteachers.dtos.lessonDto.LessonResponseDTO;
import com.forteachers.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;


    @PostMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> createLesson(@RequestBody LessonRequestDTO lessonRequestDTO,@PathVariable Long id){
        return ResponseEntity.ok(lessonService.save(lessonRequestDTO, id));

    }

    @DeleteMapping("delete/{idTeacher}/{idLesson}")
    public void deleteLesson (@PathVariable Long idTeacher, @PathVariable Long idLesson){
        lessonService.delete (idTeacher, idLesson);
    }

}
