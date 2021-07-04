package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

    public final CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> get(@PathVariable("id") String id){
        return courseService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Course course){
        validateAddOrUpdateCourse(course);
        Course savedCourse = courseService.insert(course);
        return new ResponseEntity<>(new SuccessResponse(savedCourse,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Course course){
        validateAddOrUpdateCourse(course);
        Course savedCourse = courseService.update(course);
        return new ResponseEntity<>(new SuccessResponse(savedCourse,
                HttpStatus.OK.value(),
                ResponseMessage.UPDATE_SUCCESS), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Course course){
        validateDeleteCourse(course);
        courseService.delete(course);
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);
    }

    private void validateAddOrUpdateCourse(Course course){
        if (course.getId() == null) {
            throw new ValidateException("Id field must not be null");
        }
        if (course.getId().trim().isEmpty()) {
            throw new ValidateException("Id field must not be null");
        }
        if (course.getName() == null) {
            throw new ValidateException("Name field must not be null");
        }
        if (course.getName().trim().isEmpty()) {
            throw new ValidateException("Name field must not be blank");
        }
    }

    private void validateDeleteCourse(Course course) {
        if (course.getId() == null) {
            throw new ValidateException("Id field must not be null");
        }
        if (course.getId().trim().isEmpty()) {
            throw new ValidateException("Id field must not be blank");
        }
    }
}
