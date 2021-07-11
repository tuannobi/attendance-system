package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, String> {

    List<StudentClass> getStudentClassByClassId(String classId);
}
