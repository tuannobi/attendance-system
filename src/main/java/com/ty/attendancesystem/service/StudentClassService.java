package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.StudentClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentClassService extends BaseService<StudentClass, String> {
    List<StudentClass> getStudentClassByClassId(String classId);
    void importStudents(MultipartFile multipartFile) throws IOException;
}
