//package com.ty.attendancesystem.controller;
//
//import com.ty.attendancesystem.constant.ResponseMessage;
//import com.ty.attendancesystem.message.SuccessResponse;
//import com.ty.attendancesystem.model.TimeTableCourse;
//import com.ty.attendancesystem.service.TimeTableCourseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/timetablecourse")
//public class TimeTableCourseRestController {
//
//    private TimeTableCourseService timeTableCourseService;
//
//    @Autowired
//    public TimeTableCourseRestController(TimeTableCourseService timeTableCourseService) {
//        this.timeTableCourseService = timeTableCourseService;
//    }
//
//    @GetMapping
//    public List<TimeTableCourse> getAll(){
//        return timeTableCourseService.findAll();
//    }
//
//    @GetMapping("/student/{studentId}/{year}/{semester}")
//    public List<TimeTableCourse> getTimeTableByStudentId(@PathVariable("studentId") String studentId,
//                                                         @PathVariable("year") int year,
//                                                         @PathVariable("semester") int semester){
//        return timeTableCourseService.getTimeTable(studentId, year, semester);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> add(@RequestBody TimeTableCourse timeTableCourse) {
//        //missing validate
//        TimeTableCourse savedTimeTableCourse = timeTableCourseService.insert(timeTableCourse);
//        return new ResponseEntity<>(new SuccessResponse(savedTimeTableCourse,
//                HttpStatus.CREATED.value(),
//                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
//    }
//
//    @PutMapping
//    public ResponseEntity<?> update(@RequestBody TimeTableCourse timeTableCourse) {
//        //missing validate
//        TimeTableCourse savedTimeTableCourse = timeTableCourseService.update(timeTableCourse);
//        return new ResponseEntity<>(new SuccessResponse(savedTimeTableCourse,
//                HttpStatus.OK.value(),
//                ResponseMessage.UPDATE_SUCCESS), HttpStatus.OK);
//    }
//}
