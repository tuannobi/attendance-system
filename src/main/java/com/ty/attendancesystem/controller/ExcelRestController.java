package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.helper.ExcelHelper;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.service.ClassService;
import com.ty.attendancesystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelRestController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;

    @PostMapping("/courses")
    public ResponseEntity<?> importCourses(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelHelper.hasExcelFormat(multipartFile)){
            courseService.save(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/classes")
    public ResponseEntity<?> importClasses(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelHelper.hasExcelFormat(multipartFile)){
            classService.save(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }
}
