package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.TimeTable;
import com.ty.attendancesystem.model.TimeTableCourse;
import com.ty.attendancesystem.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetables")
public class TimeTableRestController {

    private TimeTableService timeTableService;

    @Autowired
    public TimeTableRestController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @GetMapping("/student/{studentId}/{year}/{semester}")
    public TimeTable getTimeTableByStudentId(@PathVariable("studentId") String studentId,
                                                         @PathVariable("year") int year,
                                                         @PathVariable("semester") int semester){
        return timeTableService.getTimeTable(studentId, year, semester);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TimeTable timeTable) {
        for(int i=0;i<timeTable.getTimeTableCourses().size();i++){
            timeTable.getTimeTableCourses().get(i).setTimeTable(timeTable);
        }
        TimeTable savedTimeTable = timeTableService.insert(timeTable);
        return new ResponseEntity<>(new SuccessResponse(savedTimeTable,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }
}
