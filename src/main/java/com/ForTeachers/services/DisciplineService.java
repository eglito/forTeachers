package com.ForTeachers.services;

import com.ForTeachers.adapters.outputAdapters.DisciplineEntity;
import com.ForTeachers.adapters.outputAdapters.TeacherEntity;
import com.ForTeachers.dtos.disciplineDto.DisciplineRequestDTO;
import com.ForTeachers.dtos.disciplineDto.DisciplineResponseDTO;
import com.ForTeachers.mapper.DisciplineMapper;
import com.ForTeachers.persistence.DisciplinePersistence;
import com.ForTeachers.repositorios.DisciplineRepository;
import com.ForTeachers.repositorios.TeacherRepository;
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
