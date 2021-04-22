package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.AttendanceDetail;

import java.util.List;
import java.util.Optional;

public interface AttendanceDetailService extends BaseService<AttendanceDetail, Long> {
    AttendanceDetail insert(AttendanceDetail attendanceDetail);
    List<AttendanceDetail> getAttendanceDetailsStudent(String studentId);
    AttendanceDetail updateAttendanceDetails(AttendanceDetail attendanceDetail);
}
