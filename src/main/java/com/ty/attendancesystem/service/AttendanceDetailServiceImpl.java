package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.AttendanceStatus;
import com.ty.attendancesystem.exception.ServiceException;
import com.ty.attendancesystem.model.AttendanceDetail;
import com.ty.attendancesystem.repository.AttendanceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
//    if (!checkIfStudentIsTakeAttendanceOrNot(attendanceDetail.getStudent().getId(),attendanceDetail.getClazz().getId())){
      attendanceDetail.setTime(LocalDateTime.now());
      attendanceDetail.setStatus(AttendanceStatus.ATTENDED);
      AttendanceDetail result = attendanceDetailRepository.save(attendanceDetail);
      attendanceDetailRepository.refresh(result);
      return result;
//    } else {
//      throw new ServiceException("Student user took attendance in this class before");
//    }
  }

  @Transactional
  @Override
  public int updateAllAbsent(List<AttendanceDetail> attendanceDetails) {
    for (AttendanceDetail attendanceDetail: attendanceDetails) {
      attendanceDetail.setTime(LocalDateTime.now());
      attendanceDetail.setStatus(AttendanceStatus.ABSENT);
    }
    List<AttendanceDetail> attendanceDetails1 = attendanceDetailRepository.saveAll(attendanceDetails);
    return attendanceDetails1.size();
  }

  @Transactional
  @Override
  public int deleteStudentUpdatedAbsentBefore(String studentId, String classId) {
    return attendanceDetailRepository.deleteStudentUpdatedAbsentBefore(studentId, classId);
  }

  @Transactional
  @Override
  public int updateStudentPresent(String studentId, String classId) {
    return attendanceDetailRepository.updateStudentPresent(studentId,classId);
  }

  @Override
  public List<AttendanceDetail> getAttendanceDetailsStudent(String studentId) {
    return attendanceDetailRepository.getAttendanceDetailsByStudent_IdOrderByTimeAsc(studentId);
  }

  @Override
  public AttendanceDetail updateAttendanceDetails(AttendanceDetail attendanceDetail) {
    return attendanceDetailRepository.updateAttendanceDetails(attendanceDetail.getId(), attendanceDetail.getStatus());
  }

  @Transactional(readOnly = true)
  @Override
  public boolean checkIfStudentIsTakeAttendanceOrNot(String studentId, String classId) {
    int resultCount = attendanceDetailRepository.checkIfStudentIsTakeAttendanceOrNot(studentId, classId);
    if (resultCount>0){
      return true;
    }
    return false;
  }

  @Override
  public List<AttendanceDetail> getAttendanceDetailsByClazz_Id(String classId) {
    return attendanceDetailRepository.getAttendanceDetailsByClazz_Id(classId);
  }

  @Transactional(readOnly = true)
  @Override
  public int countStatusIsPresentByStudentByClass(String classId, String studentId) {
    return attendanceDetailRepository.countStatusIsPresentByStudentByClass(classId, studentId);
  }

  @Transactional(readOnly = true)
  @Override
  public int countStatusIsAbsentByStudentByClass(String classId, String studentId) {
    return attendanceDetailRepository.countStatusIsAbsentByStudentByClass(classId, studentId);
  }

}
