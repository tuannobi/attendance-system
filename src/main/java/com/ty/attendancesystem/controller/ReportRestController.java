package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.model.report.StatusObject;
import com.ty.attendancesystem.model.report.Student;
import com.ty.attendancesystem.service.AttendanceDetailService;
import com.ty.attendancesystem.service.StudentClassService;
import com.ty.attendancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/reports")
@RestController
public class ReportRestController {

    private final UserService userService;
    private final StudentClassService studentClassService;
    private final AttendanceDetailService attendanceDetailService;

    @Autowired
    public ReportRestController(UserService userService, StudentClassService studentClassService, AttendanceDetailService attendanceDetailService) {
        this.userService = userService;
        this.studentClassService = studentClassService;
        this.attendanceDetailService = attendanceDetailService;
    }

    @GetMapping("/status/{classId}")
    public StatusObject get(@PathVariable("classId") String classId) {
        //
        List<Student> students = new ArrayList<>();
        // get all student from class
        List<StudentClass> studentClasses = studentClassService.getStudentClassByClassId(classId);
        int totalPresent = 0;
        int totalAbsent = 0;
        for(StudentClass studentClass: studentClasses) {
            Student student = new Student();
            Optional<User> tempUser = userService.findById(studentClass.getStudentUserId());
            if (tempUser.isPresent()) {
                student.setId(tempUser.get().getId());
                student.setName(tempUser.get().getFullName());
            }
            int present = attendanceDetailService.countStatusIsPresentByStudentByClass(classId,tempUser.get().getId());
            student.setPresent(present);
            int absent = attendanceDetailService.countStatusIsAbsentByStudentByClass(classId,tempUser.get().getId());
            student.setAbsent(present);
            totalPresent += present;
            totalAbsent += absent;
            students.add(student);
        }
        StatusObject statusObject = new StatusObject();
        statusObject.setStudents(students);
        statusObject.setClazzId(classId);
        statusObject.setAbsent(totalAbsent);
        statusObject.setPresent(totalPresent);
        return statusObject;
    }
}
