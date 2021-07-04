package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.Class;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClassService extends BaseService<Class, String> {
    Class insert(Class course);
    Class update(Class course);
    List<String> findClassIdByTeacherUsername(String id);
    List<Class> findClassesByCourseId(String courseId);
    void save(MultipartFile multipartFile) throws IOException;
}
