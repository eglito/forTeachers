package com.forteachers.adapters.inputAdapters;

import com.forteachers.dtos.disciplineDto.DisciplineRequestDTO;
import com.forteachers.dtos.disciplineDto.DisciplineResponseDTO;
import com.forteachers.services.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @PostMapping("/{id}")
    public ResponseEntity<DisciplineResponseDTO> createDiscipline(@RequestBody DisciplineRequestDTO disciplineRequestDTO, @PathVariable Long id){
        return ResponseEntity.ok (disciplineService.save(disciplineRequestDTO, id));
    }

    @DeleteMapping("/delete/{teacherId}/{disciplineId}")
    public void deleteDiscipline(@PathVariable Long idTeacher, @PathVariable Long idDiscpline){
        disciplineService.delete (idTeacher, idDiscpline);
    }

}
