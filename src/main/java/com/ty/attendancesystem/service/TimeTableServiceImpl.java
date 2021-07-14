package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.TimeTableCourseStatus;
import com.ty.attendancesystem.helper.ExcelHelper;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.model.TimeTable;
import com.ty.attendancesystem.model.TimeTableCourse;
import com.ty.attendancesystem.repository.StudentClassRepository;
import com.ty.attendancesystem.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TimeTableServiceImpl extends BaseServiceImpl<TimeTable,Long> implements TimeTableService {

    private TimeTableRepository timeTableRepository;
    private StudentClassRepository studentClassRepository;

    @Autowired
    public TimeTableServiceImpl(TimeTableRepository timeTableRepository, StudentClassRepository studentClassRepository) {
        this.timeTableRepository = timeTableRepository;
        this.studentClassRepository = studentClassRepository;
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
    public List<TimeTable> getTimeTable(String studentId, int year, int semester) {
        return timeTableRepository.getTimeTableByUserIdAndYearAndSemesterOrderByUserId(studentId,year,semester);
    }

    @Override
    public void save(MultipartFile multipartFile) throws IOException {
        List<TimeTable> timeTables = ExcelHelper.excelToTimeTable(multipartFile.getInputStream());
        for (TimeTable timeTable: timeTables) {
            for(int i=0;i<timeTable.getTimeTableCourses().size();i++){
                timeTable.getTimeTableCourses().get(i).setTimeTable(timeTable);
                StudentClass studentClass = new StudentClass();
                studentClass.setStudentUserId(timeTable.getUserId());
                studentClass.setClassId(timeTable.getTimeTableCourses().get(i).getClazz().getId());
                studentClassRepository.save(studentClass);
            }
        }
        timeTableRepository.saveAll(timeTables);
    }
}
