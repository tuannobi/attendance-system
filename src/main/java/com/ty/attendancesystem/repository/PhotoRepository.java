package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> getPhotosByStudentId(String studentId);
}
