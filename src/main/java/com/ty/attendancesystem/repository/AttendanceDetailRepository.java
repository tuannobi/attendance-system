package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.config.CustomRepository;
import com.ty.attendancesystem.model.AttendanceDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceDetailRepository extends CustomRepository<AttendanceDetail, Long> {

    @Query(value = "select count(*) from attendance_detail " +
            "where class_id= :classId and status = 1 " +
            "and student_user_id= :studentId and " +
            "time\\:\\:date=current_date",nativeQuery = true)
    int checkIfStudentIsTakeAttendanceOrNot(String studentId, String classId);

    @Modifying
    @Query(value = "delete from attendance_detail where status=0 and class_id= :classId " +
            "and student_user_id= :studentId and " +
            "time\\:\\:date=current_date", nativeQuery = true)
    int deleteStudentUpdatedAbsentBefore(String studentId, String classId);

    //important
    @Modifying
    @Query(value = "update attendance_detail set status=1 where class_id= :classId " +
            "and student_user_id= :studentId and " +
            "time\\:\\:date=current_date",nativeQuery = true)
    int updateStudentPresent(String studentId, String classId);

    @Query(value = "select count(*) from attendance_detail where status=0 and class_id= :classId " +
            "and student_user_id= :studentId and " +
            "time\\:\\:date=current_date", nativeQuery = true)
    int checkAbsentBefore(String classId, String studentId);

    List<AttendanceDetail> getAttendanceDetailsByStudent_IdOrderByTimeAsc(String studentId);

    @Modifying
    @Query(value = "update attendance_detail " +
            "set status= :status " +
            "where id= :id ", nativeQuery = true)
    AttendanceDetail updateAttendanceDetails(Long id, int status);

    List<AttendanceDetail> getAttendanceDetailsByClazz_Id(String classId);

    @Query(value = "select count (*) from attendance_detail where status = 1 and class_id = :classId and student_user_id = :studentId",nativeQuery = true)
    int countStatusIsPresentByStudentByClass(String classId, String studentId);

    @Query(value = "select count (*) from attendance_detail where status = 0 and class_id = :classId and student_user_id = :studentId", nativeQuery = true)
    int countStatusIsAbsentByStudentByClass(String classId, String studentId);
}