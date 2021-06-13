package com.ty.attendancesystem.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class StudentClassId implements Serializable {
    @Column(name = "student_user_id")
    private String studentUserId;

    @Column(name = "class_id")
    private String classId;
}
