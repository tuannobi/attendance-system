package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseService extends BaseService<Course, String> {
    Course insert(Course course);
    Course update(Course course);
    void save(MultipartFile multipartFile) throws IOException;
}
