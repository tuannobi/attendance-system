package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Student;
import com.ty.attendancesystem.repository.AdminRepository;
import com.ty.attendancesystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Long> implements StudentService {

  private StudentRepository studentRepository;

  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository){
    this.studentRepository = studentRepository;
  }

  @Override
  protected JpaRepository<Student, Long> getRepository() {
    return this.studentRepository;
  }
}
