package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.model.TimeTable;
import com.ty.attendancesystem.model.TimeTableCourse;
import com.ty.attendancesystem.repository.TimeTableCourseRepository;
import com.ty.attendancesystem.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestRestController {

  @Autowired
  public TimeTableService timeTableService;

  @Autowired
  public TimeTableCourseRepository timeTableCourseRepository;

  @GetMapping("/api/test/area1")
  public String userAccess() {
    return ">>> This is for Teacher or Admin";
  }

  @GetMapping("/api/test/area2")
  public String projectManagementAccess() {
    return ">>> This is only for Student";
  }

  @GetMapping("/api/test/area3")
  public String adminAccess() {
    return ">>> This is only for Admin";
  }

//  @GetMapping("/api/test/start")
//  public String getTest(){
//    TimeTable timeTable = new TimeTable();
//    timeTable.setUserId("17521284");
//    timeTable.setStatus(1);
//    List<TimeTableCourse> timeTableCourses = new ArrayList<>(Arrays.asList(
//            new TimeTableCourse(timeTable,new Course("ENG"),2,3,4)
//    ));
//    timeTable.setTimeTableCourses(timeTableCourses);
//    timeTableService.save(timeTable);
//    return "success";
//  }

//  @GetMapping("/api/test/getTKB")
//  public List<TimeTableCourse> getTKB(){
//    return timeTableCourseRepository.getTimeTableCourseByTimeTable_UserId("17521284");
//  }
}
