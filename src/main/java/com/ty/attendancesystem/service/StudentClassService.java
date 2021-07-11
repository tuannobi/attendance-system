package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.StudentClass;

import java.util.List;

public interface StudentClassService extends BaseService<StudentClass, String> {
    List<StudentClass> getStudentClassByClassId(String classId);
}
