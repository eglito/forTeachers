package com.ForTeachers.services;

import com.ForTeachers.mapper.UserMapper;
import com.ForTeachers.persistence.TeacherPersistence;
import com.ForTeachers.adapters.outputAdapters.TeacherEntity;
import com.ForTeachers.dtos.EmailResponseDTO;
import com.ForTeachers.dtos.userDto.UserResponseDTO;
import com.ForTeachers.dtos.userDto.UserRequestDTO;
import com.ForTeachers.enums.UserType;
import com.ForTeachers.repositorios.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements TeacherPersistence {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public void save(UserRequestDTO userRequestDTO){

        if(userRequestDTO.userType () == null){
            throw new IllegalArgumentException ("Requisição nula");
        } else if (userRequestDTO.userType () == UserType.STUDENT) {
            throw new IllegalArgumentException ("No momento, apenas professores podem criar contas");
        }

        UserMapper entity = new UserMapper ();
        teacherRepository.save (entity.toEntity(userRequestDTO));

        entity.toResponseDTO (entity.toEntity (userRequestDTO));

    }

    public UserResponseDTO findById (Long id){

        TeacherEntity teacherEntity = teacherRepository.findById(id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado!"));

        UserMapper userMapper = new UserMapper ();

        return userMapper.toResponseDTO(teacherEntity);
    }

    public void updateTeacher(UserRequestDTO userRequestDTO, Long id){

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

        UserMapper userMapper = new UserMapper ();

        userMapper.toResponseDTO (teacherEntity);

    }

    public List<EmailResponseDTO> getAllEmails(){

        List<TeacherEntity> teachers = teacherRepository.findAll ();
        List<EmailResponseDTO> listEmails = teachers.stream ()
                .map (email -> new EmailResponseDTO (email.getEmail ()))
                .toList ();

        return listEmails;
    }

    public void deleteTeacher(Long id){

        if(!teacherRepository.existsById(id)){
            throw new RuntimeException ("Usuário não encontrado");
        }

        teacherRepository.deleteById(id);
    }

}
