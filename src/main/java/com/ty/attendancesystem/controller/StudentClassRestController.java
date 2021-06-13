package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.service.StudentClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studentclasses")
public class StudentClassRestController {

    private StudentClassService studentClassService;

    public StudentClassRestController(StudentClassService studentClassService){
        this.studentClassService = studentClassService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody StudentClass studentClass){
        StudentClass savedStudentClass = studentClassService.save(studentClass);
        return new ResponseEntity<>(new SuccessResponse(savedStudentClass,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }
}
