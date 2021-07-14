package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.TimeTableCourseStatus;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.model.TimeTableCourse;
import com.ty.attendancesystem.repository.StudentClassRepository;
import com.ty.attendancesystem.repository.TimeTableCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeTableCourseServiceImpl extends BaseServiceImpl<TimeTableCourse, Long> implements TimeTableCourseService{

    private TimeTableCourseRepository timeTableCourseRepository;
    private StudentClassRepository studentClassRepository;

    @Autowired
    public TimeTableCourseServiceImpl(TimeTableCourseRepository timeTableCourseRepository,StudentClassRepository studentClassRepository) {
        this.timeTableCourseRepository = timeTableCourseRepository;
        this.studentClassRepository = studentClassRepository;
    }

    @Override
    protected JpaRepository<TimeTableCourse, Long> getRepository() {
        return this.timeTableCourseRepository;
    }

//    @Override
//    public List<TimeTableCourse> getTimeTable(String studentId, int year, int semester) {
//        return timeTableCourseRepository.getTimeTableCourseByTimeTable_UserIdAndTimeTable_YearAndTimeTable_SemesterOrderByDayOfWeekAsc(studentId, year, semester);
//    }

    @Transactional
    @Override
    public TimeTableCourse insert(TimeTableCourse timeTableCourse) {
        //validate
        String studentId = timeTableCourseRepository.getStudentIdFromTableCourse(timeTableCourse.getId());
        StudentClass studentClass = new StudentClass();
        studentClass.setStudentUserId(studentId);
        studentClass.setClassId(timeTableCourse.getClazz().getId());
        studentClassRepository.save(studentClass);
        return timeTableCourseRepository.save(timeTableCourse);
    }

    @Transactional
    @Override
    public TimeTableCourse update(TimeTableCourse timeTableCourse) {
        //validate
        return timeTableCourseRepository.save(timeTableCourse);
    }

    @Transactional(readOnly = true)
    @Override
    public String getStudentIdFromTableCourse(Long timeTableCourse) {
        return timeTableCourseRepository.getStudentIdFromTableCourse(timeTableCourse);
    }
}
