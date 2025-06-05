package com.forteachers.services;

import com.forteachers.adapters.outputAdapters.ClassroomEntity;
import com.forteachers.adapters.outputAdapters.TeacherEntity;
import com.forteachers.dtos.classroomDto.ClassroomRequestDTO;
import com.forteachers.dtos.classroomDto.ClassroomResponseDTO;
import com.forteachers.mapper.ClassroomMapper;
import com.forteachers.persistence.ClassroomPersistence;
import com.forteachers.repositories.ClassroomRepository;
import com.forteachers.repositories.TeacherRepository;
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
        classroomEntity.setTeacher (teacherEntity);
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
