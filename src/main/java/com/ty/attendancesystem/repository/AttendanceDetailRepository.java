package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.config.CustomRepository;
import com.ty.attendancesystem.model.AttendanceDetail;
import org.springframework.data.jpa.repository.Query;

public interface AttendanceDetailRepository extends CustomRepository<AttendanceDetail, Long> {

    @Query(value = "select count(*) from attendance_detail " +
            "where class_id= :classId " +
            "and student_user_id= :studentId and " +
            "time\\:\\:date=current_date",nativeQuery = true)
    int checkIfStudentIsTakeAttendanceOrNot(String studentId, String classId);
}