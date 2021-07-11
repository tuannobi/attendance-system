package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ParentSendingMode;
import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.FailResponse;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.message.mail.MailRequest;
import com.ty.attendancesystem.message.sms.SmsRequest;
import com.ty.attendancesystem.model.AttendanceDetail;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.AttendanceDetailService;
import com.ty.attendancesystem.service.UserService;
import com.ty.attendancesystem.service.mail.MailSenderService;
import com.ty.attendancesystem.service.sms.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SmsSenderService smsSenderService;

    private final UserService userService;

    @Autowired
    public AttendanceDetailRestController(AttendanceDetailService attendanceDetailService,
                                          MailSenderService mailSenderService, SmsSenderService smsSenderService, UserService userService){
        this.attendanceDetailService = attendanceDetailService;
        this.mailSenderService = mailSenderService;
        this.smsSenderService = smsSenderService;
        this.userService = userService;
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
    public ResponseEntity<?> add(@RequestBody AttendanceDetail attendanceDetail,
                                 @RequestParam(name = "mode", required = false) String mode) {
        validateAddAttendance(attendanceDetail);
        AttendanceDetail savedAttendance = attendanceDetailService.insert(attendanceDetail);
        if (savedAttendance != null) {
                MailRequest mailRequest = new MailRequest();
                //set for student email
                mailRequest.setSubject("Attendance Notification");
                String messageContent = "Học viên " + savedAttendance.getStudent().getFullName() + " với ID "+savedAttendance.getStudent().getId()
                        + " đã điểm danh thành công lớp học với mã "+ savedAttendance.getClazz().getId()
                        + " vào lúc " + savedAttendance.getTime();
                mailRequest.setContent(messageContent);
                mailRequest.setFromEmail("noreply@gmail.com");
                mailRequest.setToEmail(savedAttendance.getStudent().getEmail());
                mailSenderService.sendEmail(mailRequest);
                //set for parent email or sms
                if (mode != null) {
                    System.out.println("MODE ======================== "+ mode);
                    int modeEmailOrPhone = Integer.parseInt(mode);
                    Optional<User> parentUser = userService.findById(savedAttendance.getStudent().getParentId());
                    if (modeEmailOrPhone == ParentSendingMode.SMS && parentUser.get().getPhone() != null) {
                        SmsRequest smsRequest = new SmsRequest(parentUser.get().getPhone(), messageContent);
                        smsSenderService.sendSms(smsRequest);
                    } else if (modeEmailOrPhone == ParentSendingMode.MAIL && parentUser.get().getEmail() != null) {
                        mailRequest.setToEmail(parentUser.get().getEmail());
                        mailSenderService.sendEmail(mailRequest);
                    } else if (modeEmailOrPhone == ParentSendingMode.ALL && parentUser.get().getPhone() != null && parentUser.get().getEmail() != null) {
                        mailRequest.setToEmail(parentUser.get().getEmail());
                        mailSenderService.sendEmail(mailRequest);

                        SmsRequest smsRequest = new SmsRequest(parentUser.get().getPhone(), messageContent);
                        smsSenderService.sendSms(smsRequest);
                    }
                }
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
