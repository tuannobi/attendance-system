package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.TimeTableCourseStatus;
import com.ty.attendancesystem.model.TimeTable;
import com.ty.attendancesystem.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimeTableServiceImpl extends BaseServiceImpl<TimeTable,Long> implements TimeTableService {

    private TimeTableRepository timeTableRepository;

    @Autowired
    public TimeTableServiceImpl(TimeTableRepository timeTableRepository) {
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    protected JpaRepository<TimeTable, Long> getRepository() {
        return this.timeTableRepository;
    }

    @Transactional
    @Override
    public TimeTable insert(TimeTable timeTable) {
        timeTable.setStatus(TimeTableCourseStatus.VALID);
        return timeTableRepository.save(timeTable);
    }

    @Transactional
    @Override
    public TimeTable getTimeTable(String studentId, int year, int semester) {
        return timeTableRepository.getTimeTableByUserIdAndYearAndSemester(studentId,year,semester);
    }
}
