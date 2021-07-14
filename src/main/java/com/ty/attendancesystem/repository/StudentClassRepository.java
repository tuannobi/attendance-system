package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, String> {

    List<StudentClass> getStudentClassByClassId(String classId);

    @Query(value = "delete from student_class where student_user_id = ?1 and class_id = ?2", nativeQuery = true)
    int deleteStudentFromClassById(String studentId, String classId);
}
