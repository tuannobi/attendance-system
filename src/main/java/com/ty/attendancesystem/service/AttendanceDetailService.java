package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.AttendanceDetail;

import java.util.List;
import java.util.Optional;

public interface AttendanceDetailService extends BaseService<AttendanceDetail, Long> {
    AttendanceDetail insert(AttendanceDetail attendanceDetail);
    int updateAllAbsent(List<AttendanceDetail> attendanceDetails);
    int checkAbsentBefore(String classId, String studentId);
    void deleteStudentUpdatedAbsentBefore(String studentId, String classId);
    int updateStudentPresent(String studentId, String classId);
    List<AttendanceDetail> getAttendanceDetailsStudent(String studentId);
    AttendanceDetail updateAttendanceDetails(AttendanceDetail attendanceDetail);
    boolean checkIfStudentIsTakeAttendanceOrNot(String studentId, String classId);
    List<AttendanceDetail> getAttendanceDetailsByClazz_Id(String classId);
    int countStatusIsPresentByStudentByClass(String classId, String studentId);
    int countStatusIsAbsentByStudentByClass(String classId, String studentId);
}
