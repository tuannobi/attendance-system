package com.ty.attendancesystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_class")
@IdClass(StudentClassId.class)
public class StudentClass {
    @Id
    @Column(name = "student_user_id")
    private String studentUserId;

    @Id
    @Column(name = "class_id")
    private String classId;

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
