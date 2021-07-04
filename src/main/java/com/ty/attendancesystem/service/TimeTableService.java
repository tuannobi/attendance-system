package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.TimeTable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TimeTableService extends BaseService<TimeTable, Long> {
    TimeTable insert(TimeTable timeTable);
    List<TimeTable> getTimeTable(String studentId, int year, int semester);
    void save(MultipartFile multipartFile) throws IOException;
}
