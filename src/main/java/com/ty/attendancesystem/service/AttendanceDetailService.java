package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.AttendanceDetail;

public interface AttendanceDetailService extends BaseService<AttendanceDetail, Long> {
    AttendanceDetail insert(AttendanceDetail attendanceDetail);
}
