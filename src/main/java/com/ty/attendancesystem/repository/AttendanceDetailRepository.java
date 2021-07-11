package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.config.CustomRepository;
import com.ty.attendancesystem.model.AttendanceDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceDetailRepository extends CustomRepository<AttendanceDetail, Long> {

    @Query(value = "select count(*) from attendance_detail " +
            "where class_id= :classId " +
            "and student_user_id= :studentId and " +
            "time\\:\\:date=current_date",nativeQuery = true)
    int checkIfStudentIsTakeAttendanceOrNot(String studentId, String classId);

    List<AttendanceDetail> getAttendanceDetailsByStudent_IdOrderByTimeAsc(String studentId);

    @Modifying
    @Query(value = "update attendance_detail " +
            "set status= :status " +
            "where id= :id ", nativeQuery = true)
    AttendanceDetail updateAttendanceDetails(Long id, int status);
}