package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, String> {
    boolean existsById(String id);

    @Query(value = "select id from class where teacher_user_id =" +
            " (select id from users where username = :username)", nativeQuery = true)
    List<String> findClassIdByTeacherUsername(String username);
}
