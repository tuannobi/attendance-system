package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
    List<TimeTable> getTimeTableByUserIdAndYearAndSemesterOrderByUserId(String studentId, int year, int semester);
}
