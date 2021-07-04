package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.TimeTableCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableCourseRepository extends JpaRepository<TimeTableCourse, Long> {
//    TimeTableCourse getTimeTableCourseByTimeTable_UserIdAndTimeTable_Status(String id,int status);
//    List<TimeTableCourse> getTimeTableCourseByTimeTable_UserIdAndTimeTable_YearAndTimeTable_SemesterOrderByDayOfWeekAsc(String id, int year, int semester);
}
