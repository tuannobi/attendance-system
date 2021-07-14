package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.TimeTableCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimeTableCourseRepository extends JpaRepository<TimeTableCourse, Long> {
//    TimeTableCourse getTimeTableCourseByTimeTable_UserIdAndTimeTable_Status(String id,int status);
//    List<TimeTableCourse> getTimeTableCourseByTimeTable_UserIdAndTimeTable_YearAndTimeTable_SemesterOrderByDayOfWeekAsc(String id, int year, int semester);
        @Query(value = "select distinct users.id from users join time_table on users.id = time_table.users_id" +
                " join time_table_course on time_table.id = time_table_course.time_table_id " +
                "where time_table_course.id = :timeTableCourseId", nativeQuery = true)
        String getStudentIdFromTableCourse(Long timeTableCourseId);
}
