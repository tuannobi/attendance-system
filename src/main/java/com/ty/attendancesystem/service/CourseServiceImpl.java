package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.exception.ServiceException;
import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.model.Role;
import com.ty.attendancesystem.repository.CourseRepository;
import com.ty.attendancesystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course,String> implements CourseService {
  private CourseRepository courseRepository;

  @Autowired
  public CourseServiceImpl(CourseRepository courseRepository){
    this.courseRepository=courseRepository;
  }

  @Override
  protected JpaRepository<Course, String> getRepository() {
    return this.courseRepository;
  }


  @Transactional
  @Override
  public Course insert(Course course) {
      boolean isExistedCourse = courseRepository.existsById(course.getId());
      if (isExistedCourse) {
        throw new ServiceException("Course is existed!");
      }
    return courseRepository.save(course);
  }

  @Transactional
  @Override
  public Course update(Course course) {
    boolean isExistedCourse = courseRepository.existsById(course.getId());
    if (!isExistedCourse) {
      throw new ServiceException("Course is not existed");
    }
    return courseRepository.save(course);
  }
}
