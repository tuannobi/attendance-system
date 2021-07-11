package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.StudentClassService;
import com.ty.attendancesystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studentclasses")
public class StudentClassRestController {

    private StudentClassService studentClassService;
    private UserService userService;

    public StudentClassRestController(StudentClassService studentClassService, UserService userService){
        this.studentClassService = studentClassService;
        this.userService = userService;
    }

    @GetMapping("/students/{classId}")
    public List<User> getStudentsByClassId(@PathVariable(name = "classId") String classId) {
        List<StudentClass> studentClasses = studentClassService.getStudentClassByClassId(classId);
        List<User> users = new ArrayList<>();
        for (StudentClass studentClass: studentClasses) {
            Optional<User> user = userService.findById(studentClass.getStudentUserId());
            users.add(user.get());
        }
        return users;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody StudentClass studentClass){
        StudentClass savedStudentClass = studentClassService.save(studentClass);
        return new ResponseEntity<>(new SuccessResponse(savedStudentClass,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }
}
