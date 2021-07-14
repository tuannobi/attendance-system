package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.helper.ExcelImportHelper;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.repository.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

  @Transactional(readOnly = true)
  @Override
  public List<StudentClass> getStudentClassByClassId(String classId) {
    return studentClassRepository.getStudentClassByClassId(classId);
  }

  @Transactional
  @Override
  public void importStudents(MultipartFile multipartFile) throws IOException {
    List<StudentClass> studentClasses = ExcelImportHelper.excelToStudentClass(multipartFile.getInputStream());
    studentClassRepository.saveAll(studentClasses);
  }
}
