package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.AttendanceDetail;
import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.service.AttendanceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceDetailRestController {

    public AttendanceDetailService attendanceDetailService;

    public AttendanceDetailRestController(AttendanceDetailService attendanceDetailService){
        this.attendanceDetailService = attendanceDetailService;
    }

    @GetMapping("/{id}")
    public Optional<AttendanceDetail> get(@PathVariable("id") Long id){
        return attendanceDetailService.findById(id);
    }

    @GetMapping
    public List<AttendanceDetail> getAll(){
        return attendanceDetailService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(AttendanceDetail attendanceDetail) {
        validateAddAttendance(attendanceDetail);
        AttendanceDetail savedStudent = attendanceDetailService.insert(attendanceDetail);
        return new ResponseEntity<>(new SuccessResponse(savedStudent,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }

    private void validateAddAttendance(AttendanceDetail attendanceDetail) {
        if (attendanceDetail.getStatus() == null) {
            throw new ValidateException("Status field must not be null");
        }
        if (attendanceDetail.getStatus().trim().isEmpty()) {
            throw new ValidateException("Status field must not be blank");
        }
    }
}
