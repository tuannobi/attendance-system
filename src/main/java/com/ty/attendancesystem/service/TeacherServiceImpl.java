package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Teacher;
import com.ty.attendancesystem.repository.AdminRepository;
import com.ty.attendancesystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Long> implements TeacherService {

  private TeacherRepository teacherRepository;

  @Autowired
  public TeacherServiceImpl(TeacherRepository teacherRepository){
    this.teacherRepository = teacherRepository;
  }

  @Override
  protected JpaRepository<Teacher, Long> getRepository() {
    return this.teacherRepository;
  }
}
