package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentParentRepository extends JpaRepository<StudentParent, Long> {
}
