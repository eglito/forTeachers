package com.ForTeachers.services;

import com.ForTeachers.adapters.outputAdapters.TeacherEntity;
import com.ForTeachers.dtos.EmailResponseDTO;
import com.ForTeachers.dtos.UserResponseDTO;
import com.ForTeachers.dtos.UserRequestDTO;
import com.ForTeachers.enums.UserType;
import com.ForTeachers.repositorios.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<UserResponseDTO> creatTeacher(UserRequestDTO userRequestDTO){

        if(userRequestDTO.userType () == null){
            throw new IllegalArgumentException ("Requisição nula");
        } else if (userRequestDTO.userType () == UserType.STUDENT) {
            throw new IllegalArgumentException ("No momento, apenas professores podem criar contas");
        }

        TeacherEntity newTeacherEntity = new TeacherEntity();
        newTeacherEntity.setFirstName (userRequestDTO.firstName ());
        newTeacherEntity.setLastName (userRequestDTO.lastName ());
        newTeacherEntity.setEmail (userRequestDTO.email ());
        newTeacherEntity.setPassword (userRequestDTO.password ());
        newTeacherEntity.setUserType (userRequestDTO.userType ());

        teacherRepository.save (newTeacherEntity);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                newTeacherEntity.getFirstName(),
                newTeacherEntity.getEmail (),
                newTeacherEntity.getUserType ()
        );

        return ResponseEntity.ok (userResponseDTO);
    }

    public ResponseEntity<UserResponseDTO> getUser (Long id){

        TeacherEntity teacherEntity = teacherRepository.findById(id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado!"));

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                teacherEntity.getFirstName(),
                teacherEntity.getEmail (),
                teacherEntity.getUserType ()
        );

        return ResponseEntity.ok (userResponseDTO);
    }

    public ResponseEntity<UserResponseDTO> updateTeacher(UserRequestDTO userRequestDTO, Long id){

        TeacherEntity teacherEntity = teacherRepository.findById(id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado"));

        if(!teacherEntity.getFirstName().equals(userRequestDTO.firstName ())){
            teacherEntity.setFirstName(userRequestDTO.firstName ());
        }
        if(!teacherEntity.getLastName().equals(userRequestDTO.lastName())){
            teacherEntity.setLastName(userRequestDTO.lastName ());
        }
        if(!teacherEntity.getEmail ().equals(userRequestDTO.email ())){
            teacherEntity.setEmail(userRequestDTO.email ());
        }
        if(!teacherEntity.getPassword ().equals(userRequestDTO.password ())){
            teacherEntity.setPassword (userRequestDTO.password ());
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                teacherEntity.getFirstName(),
                teacherEntity.getEmail (),
                teacherEntity.getUserType ()
        );

        return ResponseEntity.ok (userResponseDTO);

    }

    public ResponseEntity<List<EmailResponseDTO>> getAllEmails(){

        List<TeacherEntity> teachers = teacherRepository.findAll ();
        List<EmailResponseDTO> listEmails = teachers.stream ()
                .map (email -> new EmailResponseDTO (email.getEmail ()))
                .toList ();

        return ResponseEntity.ok (listEmails);
    }

    public ResponseEntity<Void> deleteTeacher(Long id){

        if(!teacherRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        teacherRepository.deleteById(id);
        return ResponseEntity.ok().build ();
    }

}
