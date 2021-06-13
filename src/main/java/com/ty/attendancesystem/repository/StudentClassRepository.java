package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClass, String> {
}
