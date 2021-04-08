package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.AttendanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetail, Long> {
}
