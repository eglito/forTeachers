package com.ForTeachers.services;

import com.ForTeachers.adapters.outputAdapters.ClassroomEntity;
import com.ForTeachers.adapters.outputAdapters.TeacherEntity;
import com.ForTeachers.dtos.classroomDTO.ClassroomRequestDTO;
import com.ForTeachers.dtos.classroomDTO.ClassroomResponseDTO;
import com.ForTeachers.mapper.ClassroomMapper;
import com.ForTeachers.persistence.ClassroomPersistence;
import com.ForTeachers.repositorios.ClassroomRepository;
import com.ForTeachers.repositorios.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService implements ClassroomPersistence {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    public ClassroomResponseDTO save (ClassroomRequestDTO requestDTO, Long id){

        TeacherEntity teacherEntity = teacherRepository.findById (id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado"));

        ClassroomMapper mapper = new ClassroomMapper ();
        ClassroomEntity classroomEntity =  mapper.toEntity (requestDTO);
        classroomRepository.save (classroomEntity);
        teacherEntity.addClassroom (classroomEntity);

        return mapper.toResponse(classroomEntity);

    }

    public void delete (Long teacherId, Long classroomId){

        TeacherEntity teacherEntity = teacherRepository.findById (teacherId)
                .orElseThrow (() -> new RuntimeException ("Id referente à Teacher não foi encontrado"));

        ClassroomEntity classroomEntity = this.findaClassroomById(classroomId);
        TeacherEntity teacherClassroom = classroomEntity.getTeacher ();

        if(!teacherEntity.equals (teacherClassroom)){
            throw new RuntimeException ("Professor deve ser proprietário da disciplina para realizar a exclusão");
        }

        teacherEntity.removeClassroom(classroomEntity);
        classroomRepository.delete(classroomEntity);

    }

    private ClassroomEntity findaClassroomById(Long classroomId){

        return classroomRepository.findById (classroomId)
                .orElseThrow (() -> new RuntimeException ("Id referente à Classroom não foi encontrado"));
    }
}
