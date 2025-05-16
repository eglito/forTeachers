package com.forteachers.services;

import com.forteachers.adapters.outputAdapters.DisciplineEntity;
import com.forteachers.adapters.outputAdapters.TeacherEntity;
import com.forteachers.dtos.disciplineDto.DisciplineRequestDTO;
import com.forteachers.dtos.disciplineDto.DisciplineResponseDTO;
import com.forteachers.mapper.DisciplineMapper;
import com.forteachers.persistence.DisciplinePersistence;
import com.forteachers.repositories.DisciplineRepository;
import com.forteachers.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplineService implements DisciplinePersistence {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    public DisciplineResponseDTO save (DisciplineRequestDTO requestDTO, Long id){

        TeacherEntity teacherEntity = teacherRepository.findById (id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado"));

        DisciplineMapper disciplineMapper = new DisciplineMapper();
        DisciplineEntity disciplineEntity = disciplineMapper.toEntity(requestDTO);
        disciplineRepository.save (disciplineEntity);
        teacherEntity.addDisciplines(disciplineEntity);

        return disciplineMapper.toResponse (disciplineEntity);

    }

    public void delete(Long teacherId, Long disciplineId){

        DisciplineEntity disciplineEntity = this.findDisciplineById(disciplineId);
        TeacherEntity teacherDiscipline = disciplineEntity.getTeacher ();

        TeacherEntity teacherEntity = teacherRepository.findById (teacherId).get ();
        if(!teacherEntity.equals(teacherDiscipline)){
            throw new RuntimeException ("Professor deve ser proprietário da disciplina para realizar a exclusão");
        }

        teacherEntity.removeDisciplines(disciplineEntity);
        disciplineRepository.delete(disciplineEntity);
    }

    private DisciplineEntity findDisciplineById(Long disciplineId){
        return disciplineRepository.findById(disciplineId)
                .orElseThrow (() -> new RuntimeException ("Discipline não encontrada"));
    }
}
