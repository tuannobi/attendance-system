package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassRestController {
    private final ClassService classService;

    @Autowired
    public ClassRestController(ClassService classService){
        this.classService = classService;
    }

    @GetMapping
    public List<Class> getAll() {
        return classService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Class> get(@PathVariable("id") String id){
        return classService.findById(id);
    }

    @GetMapping("/teacher/{username}")
    public List<String> getAllByTeacher(@PathVariable("username") String id){
        return classService.findClassIdByTeacherUsername(id);
    }

    @GetMapping("/course/{courseId}")
    public List<Class> getClassesByCourseId(@PathVariable("courseId") String courseId) {
        return classService.findClassesByCourseId(courseId);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Class clazz){
        validateAddClass(clazz);
        Class savedClass = classService.insert(clazz);
        return new ResponseEntity<>(new SuccessResponse(savedClass,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Class clazz) {
        validateUpdateClass(clazz);
        Class savedClass = classService.update(clazz);
        return new ResponseEntity<>(new SuccessResponse(savedClass,
                HttpStatus.OK.value(),
                ResponseMessage.UPDATE_SUCCESS), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Class clazz){
        validateDeleteClass(clazz);
        classService.delete(clazz);
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);
    }

    private void validateDeleteClass(Class clazz) {
    }

    private void validateAddClass(Class clazz) {

    }

    private void validateUpdateClass(Class clazz){
        if (clazz.getId() == null) {
            throw new ValidateException("Id must not be null");
        }
        if (clazz.getId().trim().isEmpty()) {
            throw new ValidateException("Id must not be blank");
        }
    }
}
