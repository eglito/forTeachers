package com.forteachers.adapters.outputAdapters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invitationToken")
public class InvitationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = TeacherEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne(targetEntity = ClassroomEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "classroom_id")
    private ClassroomEntity classroom;

    private LocalDateTime expiryDate;

}
