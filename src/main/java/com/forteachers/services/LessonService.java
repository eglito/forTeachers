package com.forteachers.services;

import com.forteachers.adapters.outputAdapters.LessonEntity;
import com.forteachers.adapters.outputAdapters.TeacherEntity;
import com.forteachers.dtos.lessonDto.LessonRequestDTO;
import com.forteachers.dtos.lessonDto.LessonResponseDTO;
import com.forteachers.mapper.LessonMapper;
import com.forteachers.persistence.LessonPersistence;
import com.forteachers.repositories.LessonRepository;
import com.forteachers.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService implements LessonPersistence {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public LessonResponseDTO save (LessonRequestDTO lessonRequestDTO, Long id){

        TeacherEntity teacherEntity =  teacherRepository.findById (id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado"));

        LessonMapper mapper = new LessonMapper ();
        LessonEntity lessonEntity = mapper.toEntity (lessonRequestDTO);
        lessonEntity.setTeacher(teacherEntity);

        teacherEntity.addLesson (lessonEntity);
        lessonRepository.save(lessonEntity);

        return mapper.toResponse(lessonEntity);

    }
    public void delete (Long teacherId, Long lessonId){

        LessonEntity lessonEntity = this.findaLessonByLessonId(lessonId);
        TeacherEntity teacherLesson = lessonEntity.getTeacher ();

        TeacherEntity teacherEntity = teacherRepository.findById (teacherId).get ();
        if(!teacherEntity.equals(teacherLesson)){
           throw new RuntimeException ("Professor deve ser proprietário da atividade para realizar a exclusão");
        }

        teacherEntity.removeLesson(lessonEntity);
        lessonRepository.delete(lessonEntity);

    }

    private LessonEntity findaLessonByLessonId(Long lessonId){
        return lessonRepository.findById (lessonId)
                .orElseThrow (() -> new RuntimeException ("Lesson não encontrada!"));

    }

}

