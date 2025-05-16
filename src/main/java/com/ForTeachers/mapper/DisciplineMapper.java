package com.ForTeachers.mapper;

import com.ForTeachers.adapters.outputAdapters.DisciplineEntity;
import com.ForTeachers.dtos.disciplineDto.DisciplineRequestDTO;
import com.ForTeachers.dtos.disciplineDto.DisciplineResponseDTO;

public class DisciplineMapper {

    public DisciplineMapper(){

    }

    public DisciplineEntity toEntity (DisciplineRequestDTO requestDTO){

        DisciplineEntity discipline = new DisciplineEntity ();
        discipline.setName(requestDTO.disciplineName ());
        return discipline;
    }

    public DisciplineResponseDTO toResponse (DisciplineEntity entity){

        DisciplineResponseDTO responseDTO = new DisciplineResponseDTO(
                entity.getName (),
                entity.getTeacher().getFirstName (),
                entity.getTeacher ().getLastName (),
                entity.getTeacher ().getEmail ()
        );
        return responseDTO;

    }
}
