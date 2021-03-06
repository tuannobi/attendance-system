package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.TimeTableCourse;
import com.ty.attendancesystem.repository.StudentClassRepository;
import com.ty.attendancesystem.service.TimeTableCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/timetablecourse")
public class TimeTableCourseRestController {

    private TimeTableCourseService timeTableCourseService;
    private StudentClassRepository studentClassRepository;
    @Autowired
    public TimeTableCourseRestController(TimeTableCourseService timeTableCourseService, StudentClassRepository studentClassRepository) {
        this.timeTableCourseService = timeTableCourseService;
        this.studentClassRepository = studentClassRepository;
    }

    @GetMapping
    public List<TimeTableCourse> getAll(){
        return timeTableCourseService.findAll();
    }

    @DeleteMapping
    public void delete(@RequestBody TimeTableCourse timeTableCourse) {
        System.out.println("ENTER===============================");
        String studentId =timeTableCourseService.getStudentIdFromTableCourse(timeTableCourse.getId());
        System.out.println("================Student Id =================" + studentId);
        System.out.println("================Class Id ===================" + timeTableCourse.getClazz().getId());
        int temp = studentClassRepository.deleteStudentFromClassById(studentId,timeTableCourse.getClazz().getId());
        System.out.println("So dong da xoa =================== "+temp);
        timeTableCourseService.deleteById(timeTableCourse.getId());
    }
}
