package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
