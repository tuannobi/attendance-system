package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.TimeTable;

public interface TimeTableService extends BaseService<TimeTable, Long> {
    TimeTable insert(TimeTable timeTable);
    TimeTable getTimeTable(String studentId, int year, int semester);
}
