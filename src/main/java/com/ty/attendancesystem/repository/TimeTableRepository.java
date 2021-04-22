package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    TimeTable getTimeTableByUserIdAndYearAndSemester(String studentId, int year, int semester);
}
