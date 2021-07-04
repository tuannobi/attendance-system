package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
    boolean existsById(String id);
}
