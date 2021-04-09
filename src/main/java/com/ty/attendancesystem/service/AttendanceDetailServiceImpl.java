package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.AttendanceDetail;
import com.ty.attendancesystem.repository.AttendanceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AttendanceDetailServiceImpl extends BaseServiceImpl<AttendanceDetail,Long> implements AttendanceDetailService {
  private AttendanceDetailRepository attendanceDetailRepository;

  @Autowired
  public AttendanceDetailServiceImpl(AttendanceDetailRepository attendanceDetailRepository){
    this.attendanceDetailRepository = attendanceDetailRepository;
  }

  @Override
  protected JpaRepository<AttendanceDetail, Long> getRepository() {
    return this.attendanceDetailRepository;
  }

  @Transactional
  @Override
  public AttendanceDetail insert(AttendanceDetail attendanceDetail) {
    if (checkIfStudentAttended()){
      attendanceDetail.setTime(LocalDateTime.now());
      AttendanceDetail result = attendanceDetailRepository.save(attendanceDetail);
      attendanceDetailRepository.refresh(result);
      return result;
    }
    return null;
  }

  private boolean checkIfStudentAttended() {
    return true;
  }
}
