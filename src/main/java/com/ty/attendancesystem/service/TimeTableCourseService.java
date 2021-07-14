package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.TimeTableCourse;

import java.util.List;

public interface TimeTableCourseService extends BaseService<TimeTableCourse, Long> {
//    List<TimeTableCourse> getTimeTable(String studentId,int year, int semester);

    TimeTableCourse insert(TimeTableCourse timeTableCourse);

    TimeTableCourse update(TimeTableCourse timeTableCourse);
    String getStudentIdFromTableCourse(Long timeTableCourse);
}
