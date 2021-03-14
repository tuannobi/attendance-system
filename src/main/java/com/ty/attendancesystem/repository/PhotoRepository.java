package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
