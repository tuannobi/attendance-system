package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Role;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.repository.RoleRepository;
import com.ty.attendancesystem.repository.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentClassServiceImpl extends BaseServiceImpl<StudentClass,String> implements StudentClassService {
  private StudentClassRepository studentClassRepository;

  @Autowired
  public StudentClassServiceImpl(StudentClassRepository studentClassRepository){
    this.studentClassRepository=studentClassRepository;
  }

  @Override
  protected JpaRepository<StudentClass, String> getRepository() {
    return this.studentClassRepository;
  }
}
