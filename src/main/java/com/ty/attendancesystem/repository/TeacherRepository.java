package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
