package com.forteachers.services;

import com.forteachers.InvitationToken;
import com.forteachers.adapters.outputAdapters.ClassroomEntity;
import com.forteachers.mapper.TeacherMapper;
import com.forteachers.persistence.TeacherPersistence;
import com.forteachers.adapters.outputAdapters.TeacherEntity;
import com.forteachers.dtos.EmailResponseDTO;
import com.forteachers.dtos.userDto.UserResponseDTO;
import com.forteachers.dtos.userDto.UserRequestDTO;
import com.forteachers.enums.UserType;
import com.forteachers.repositories.ClassroomRepository;
import com.forteachers.repositories.TeacherRepository;
import com.forteachers.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService implements TeacherPersistence {

    @Autowired
    private final TeacherRepository teacherRepository;
    private PasswordBcrypt passwordBcrypt;
    private ClassroomRepository classroomRepository;
    private TokenRepository tokenRepository;

    public TeacherService(TeacherRepository teacherRepository, PasswordBcrypt passwordBcrypt, ClassroomRepository classroomRepository, TokenRepository tokenRepository) {
        this.teacherRepository = teacherRepository;
        this.passwordBcrypt = passwordBcrypt;
        this.classroomRepository = classroomRepository;
        this.tokenRepository = tokenRepository;
    }


    public UserResponseDTO save(UserRequestDTO userRequestDTO) throws IllegalAccessException {

        if(userRequestDTO.userType () == null){
            throw new IllegalArgumentException ("Requisição nula");
        } else if (userRequestDTO.userType () == UserType.STUDENT) {
            throw new IllegalArgumentException ("No momento, apenas professores podem criar contas");
        }

        TeacherEntity teacherEntity = new TeacherMapper().toEntity(userRequestDTO);
        teacherEntity.setPassword(passwordBcrypt.hashing(userRequestDTO.password()));
        teacherRepository.save(teacherEntity);

        return new TeacherMapper().toResponseDTO(teacherEntity);

    }

    public UserResponseDTO findById (Long id){

        TeacherEntity teacherEntity = teacherRepository.findById(id)
                .orElseThrow (() -> new RuntimeException ("Usuário não encontrado!"));

        TeacherMapper teacherMapper = new TeacherMapper();

        return teacherMapper.toResponseDTO(teacherEntity);
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

        TeacherMapper teacherMapper = new TeacherMapper();

        teacherMapper.toResponseDTO (teacherEntity);

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

    public String generateInvitationToken(Long teacherId, Long classroomId){

        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        ClassroomEntity classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Sala de aula não encontrada"));

        String token = UUID.randomUUID().toString();

        InvitationToken invitationToken = new InvitationToken();
        invitationToken.setToken(token);
        invitationToken.setTeacher(teacher);
        invitationToken.setClassroom(classroom);
        invitationToken.setExpiryDate(LocalDateTime.now().plusDays(2));
        tokenRepository.save(invitationToken);

        return token;
    }
}
