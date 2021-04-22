package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.FailResponse;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.message.mail.MailRequest;
import com.ty.attendancesystem.model.AttendanceDetail;
import com.ty.attendancesystem.service.AttendanceDetailService;
import com.ty.attendancesystem.service.mail.MailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceDetailRestController {

    public AttendanceDetailService attendanceDetailService;

    public MailSenderService mailSenderService;

    public AttendanceDetailRestController(AttendanceDetailService attendanceDetailService,
                                          MailSenderService mailSenderService){
        this.attendanceDetailService = attendanceDetailService;
        this.mailSenderService = mailSenderService;
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceDetail> getByStudentId(@PathVariable("studentId") String studentId){
        return attendanceDetailService.getAttendanceDetailsStudent(studentId);
    }

    @GetMapping("/{id}")
    public Optional<AttendanceDetail> get(@PathVariable("id") Long id) {
        return attendanceDetailService.findById(id);
    }

    @GetMapping
    public List<AttendanceDetail> getAll(){
        return attendanceDetailService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AttendanceDetail attendanceDetail) {
        validateAddAttendance(attendanceDetail);
        AttendanceDetail savedAttendance = attendanceDetailService.insert(attendanceDetail);
        if (savedAttendance != null) {
                MailRequest mailRequest = new MailRequest();
                mailRequest.setSubject("Attendance Notification");
                mailRequest.setContent("Student with Id "+savedAttendance.getStudent().getId()
                        + " has taken successfully attendance of class "+ savedAttendance.getClazz().getId()
                        + " at " + savedAttendance.getTime());
                mailRequest.setFromEmail("noreply@gmail.com");
                mailRequest.setToEmail(savedAttendance.getStudent().getEmail());
                mailSenderService.sendEmail(mailRequest);
                return new ResponseEntity<>(new SuccessResponse(savedAttendance,
                        HttpStatus.CREATED.value(),
                        ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new FailResponse(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ResponseMessage.ADD_FAIL),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AttendanceDetail attendanceDetail) {
        //validate
        AttendanceDetail savedAttendanceDetail = attendanceDetailService.updateAttendanceDetails(attendanceDetail);
        return new ResponseEntity<>(new SuccessResponse(savedAttendanceDetail,
                HttpStatus.OK.value(),
                ResponseMessage.UPDATE_SUCCESS), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody AttendanceDetail attendanceDetail) {
        attendanceDetailService.delete(attendanceDetail);
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);
    }

    private void validateAddAttendance(AttendanceDetail attendanceDetail) {
        if (attendanceDetail.getId() != null) {
            throw new ValidateException("Id field must be null");
        }
    }
}
