package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.Course;

public interface CourseService extends BaseService<Course, String> {
    Course insert(Course course);
    Course update(Course course);
}
