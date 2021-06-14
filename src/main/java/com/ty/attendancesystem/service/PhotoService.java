package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService extends BaseService<Photo, Long> {
    List<Photo> insert(List<MultipartFile> files, String studentId) throws IOException;
    List<Photo> getPhotosByStudent(String studentId);
}
