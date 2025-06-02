package com.forteachers;

import com.forteachers.adapters.outputAdapters.ClassroomEntity;
import com.forteachers.adapters.outputAdapters.TeacherEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "invitationToken")
public class InvitationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = TeacherEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "teacher_id")
    private TeacherEntity teacher;

    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    @ManyToOne(targetEntity = ClassroomEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "classroom_id")
    private ClassroomEntity classroom;


    private Date expiryDate;

    public InvitationToken(String token, TeacherEntity teacher, Date expiryDate) {
        this.token = token;
        this.teacher = teacher;
        this.expiryDate = expiryDate;
    }

    public InvitationToken(){

    }

    public Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance ();
        cal.setTime (new Timestamp (cal.getTime().getTime ()));
        cal.add (Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime ().getTime ());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TeacherEntity getUser() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
