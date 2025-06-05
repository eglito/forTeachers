package com.forteachers.services;

import com.forteachers.adapters.outputAdapters.InvitationToken;
import com.forteachers.adapters.outputAdapters.StudentEntity;
import com.forteachers.dtos.studentDto.StudentRequestDTO;
import com.forteachers.dtos.studentDto.StudentResponseDTO;
import com.forteachers.dtos.tokenDto.TokenResponseDTO;
import com.forteachers.enums.UserType;
import com.forteachers.mapper.StudentMapper;
import com.forteachers.persistence.StudentPersistence;
import com.forteachers.repositories.StudentRepository;
import com.forteachers.repositories.TeacherRepository;
import com.forteachers.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StudentService implements StudentPersistence {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordBcrypt passwordBcrypt;

    public StudentResponseDTO registerStudents(String token, StudentRequestDTO studentRequestDTO) throws IllegalAccessException {

        InvitationToken invitationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if(invitationToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token expirado");
        }
        if(!studentRequestDTO.userType ().equals (UserType.STUDENT)){
            throw new RuntimeException ("Usuário deve ser do tipo STUDENT");
        }

        StudentEntity student = new StudentMapper().toEntity(studentRequestDTO);

        TokenResponseDTO tokenInformation = tokenInformation (token);
        student.setTeacher (tokenInformation.teacher ());
        student.addClassroom (tokenInformation.classroom());

        tokenInformation.teacher ().addStudent (student);

        student.setPassword(passwordBcrypt.hashing(studentRequestDTO.password()));

        studentRepository.save(student);

        return new StudentMapper().toResponseDTO(student);
    }

    private TokenResponseDTO tokenInformation(String token){
        Optional<InvitationToken> invitationToken = tokenRepository.findByToken (token);
        return new TokenResponseDTO (invitationToken.get ().getTeacher (), invitationToken.get ().getClassroom ());
    }
}
