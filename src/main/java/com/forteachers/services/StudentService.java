package com.forteachers.services;

import com.forteachers.InvitationToken;
import com.forteachers.adapters.outputAdapters.StudentEntity;
import com.forteachers.dtos.userDto.UserRequestDTO;
import com.forteachers.dtos.userDto.UserResponseDTO;
import com.forteachers.mapper.StudentMapper;
import com.forteachers.persistence.StudentPersistence;
import com.forteachers.repositories.StudentRepository;
import com.forteachers.repositories.TeacherRepository;
import com.forteachers.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentService implements StudentPersistence {

    @Autowired
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private TokenRepository tokenRepository;
    private PasswordBcrypt passwordBcrypt;

    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository, TokenRepository tokenRepository,PasswordBcrypt passwordBcrypt) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.tokenRepository = tokenRepository;
        this.passwordBcrypt = passwordBcrypt;
    }

    public UserResponseDTO registerStudents(String token, UserRequestDTO userRequestDTO) throws IllegalAccessException {

        InvitationToken invitationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if(invitationToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token expirado");
        }

        StudentEntity student = new StudentMapper().toEntity(userRequestDTO);
        student.setPassword(passwordBcrypt.hashing(userRequestDTO.password()));
        studentRepository.save(student);

        return new StudentMapper().toResponseDTO(student);
    }
}
