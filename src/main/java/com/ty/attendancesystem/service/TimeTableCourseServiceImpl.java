package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.TimeTableCourseStatus;
import com.ty.attendancesystem.model.TimeTableCourse;
import com.ty.attendancesystem.repository.TimeTableCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeTableCourseServiceImpl extends BaseServiceImpl<TimeTableCourse, Long> implements TimeTableCourseService{

    private TimeTableCourseRepository timeTableCourseRepository;

    @Autowired
    public TimeTableCourseServiceImpl(TimeTableCourseRepository timeTableCourseRepository) {
        this.timeTableCourseRepository = timeTableCourseRepository;
    }

    @Override
    protected JpaRepository<TimeTableCourse, Long> getRepository() {
        return this.timeTableCourseRepository;
    }

    @Override
    public List<TimeTableCourse> getTimeTable(String studentId, int year, int semester) {
        return timeTableCourseRepository.getTimeTableCourseByTimeTable_UserIdAndTimeTable_YearAndTimeTable_SemesterOrderByDayOfWeekAsc(studentId, year, semester);
    }

    @Transactional
    @Override
    public TimeTableCourse insert(TimeTableCourse timeTableCourse) {
        //validate
        return timeTableCourseRepository.save(timeTableCourse);
    }

    @Transactional
    @Override
    public TimeTableCourse update(TimeTableCourse timeTableCourse) {
        //validate
        return timeTableCourseRepository.save(timeTableCourse);
    }
}
