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

    List<AttendanceDetail> getAttendanceDetailsByClazz_Id(String classId);

    @Query(value = "select count (*) from attendance_detail join users on attendance_detail.student_user_id = users.id join time_table on users.id = time_table.users_id where attendance_detail.status = 1 and class_id = :classId and attendance_detail.student_user_id = :studentId and time_table.year = :year and time_table.semester = :semester",nativeQuery = true)
    int countStatusIsPresentByStudentByClass(String classId, int semester, int year, String studentId);

    @Query(value = "select count (*) from attendance_detail join users on attendance_detail.student_user_id = users.id join time_table on users.id = time_table.users_id where attendance_detail.status = 0 and class_id = :classId and attendance_detail.student_user_id = :studentId and time_table.year = :year and time_table.semester = :semester", nativeQuery = true)
    int countStatusIsAbsentByStudentByClass(String classId, int semester, int year, String studentId);
}