package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.exception.ServiceException;
import com.ty.attendancesystem.helper.ExcelImportHelper;
import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

  @Override
  public void save(MultipartFile multipartFile) throws IOException {
    List<Course> courses = ExcelImportHelper.excelToCourses(multipartFile.getInputStream());
    courseRepository.saveAll(courses);
  }
}
