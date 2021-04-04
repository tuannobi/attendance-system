package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, String> {
    boolean existsById(String id);
}
