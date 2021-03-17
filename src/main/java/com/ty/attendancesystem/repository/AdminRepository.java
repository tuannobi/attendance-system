package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
