package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
