package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ParentSendingMode;
import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.FailResponse;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.message.mail.MailRequest;
import com.ty.attendancesystem.message.sms.SmsRequest;
import com.ty.attendancesystem.model.AttendanceDetail;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.Photo;
import com.ty.attendancesystem.model.StudentClass;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.AttendanceDetailService;
import com.ty.attendancesystem.service.PhotoService;
import com.ty.attendancesystem.service.StudentClassService;
import com.ty.attendancesystem.service.UserService;
import com.ty.attendancesystem.service.mail.MailSenderService;
import com.ty.attendancesystem.service.sms.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceDetailRestController {

    public AttendanceDetailService attendanceDetailService;

    public MailSenderService mailSenderService;

    private final SmsSenderService smsSenderService;

    private final UserService userService;

    private final PhotoService photoService;

    private final StudentClassService studentClassService;

    @Autowired
    public AttendanceDetailRestController(AttendanceDetailService attendanceDetailService,
                                          MailSenderService mailSenderService, SmsSenderService smsSenderService, UserService userService,
                                          PhotoService photoService, StudentClassService studentClassService){
        this.attendanceDetailService = attendanceDetailService;
        this.mailSenderService = mailSenderService;
        this.smsSenderService = smsSenderService;
        this.userService = userService;
        this.photoService = photoService;
        this.studentClassService = studentClassService;
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceDetail> getByStudentId(@PathVariable("studentId") String studentId){
        return attendanceDetailService.getAttendanceDetailsStudent(studentId);
    }

    @GetMapping("/student/class/{classId}")
    public List<AttendanceDetail> getByClassId(@PathVariable("classId") String classId) {
        return attendanceDetailService.getAttendanceDetailsByClazz_Id(classId);
    }

    @GetMapping("/{id}")
    public Optional<AttendanceDetail> get(@PathVariable("id") Long id) {
        return attendanceDetailService.findById(id);
    }

    @GetMapping
    public List<AttendanceDetail> getAll(){
        return attendanceDetailService.findAll();
    }

    @GetMapping("/check")
    public boolean check(@RequestBody AttendanceDetail attendanceDetail) {
        boolean flag =  attendanceDetailService.checkIfStudentIsTakeAttendanceOrNot(attendanceDetail.getStudent().getId(),
                attendanceDetail.getClazz().getId());
        if (!flag) {
            System.out.println("Student user took attendance in this class before");
        }
        return flag;
    }

    //only truyen vao class_Id
    @PostMapping("/absent")
    public void updateRestOfStudentIsAbsent(@RequestBody AttendanceDetail attendanceDetail) {
        List<StudentClass> studentClasses = studentClassService.getStudentClassByClassId(attendanceDetail.getClazz().getId());
        List<AttendanceDetail> attendanceDetails = new ArrayList<>();
        for (StudentClass studentClass: studentClasses) {
//            if (attendanceDetailService.checkAbsentBefore(attendanceDetail.getClazz().getId(), studentClass.getStudentUserId())<=0) {
                AttendanceDetail temp = new AttendanceDetail();
                Class clazz = new Class();
                clazz.setId(attendanceDetail.getClazz().getId());
                temp.setClazz(clazz);
                User user = new User();
                user.setId(studentClass.getStudentUserId());
                temp.setStudent(user);
                attendanceDetails.add(temp);
//            }
        }
//        if (attendanceDetails.size()>0) {
            attendanceDetailService.updateAllAbsent(attendanceDetails);
//        }
     }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AttendanceDetail attendanceDetail,
                                 @RequestParam(name = "mode", required = false) String mode) {
//        attendanceDetailService.deleteStudentUpdatedAbsentBefore(attendanceDetail.getStudent().getId(), attendanceDetail.getClazz().getId());
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

    @PostMapping("/photo")
    public List<Photo> addPhotoToAttendanceDetail(@RequestParam(name = "id") String studentId, List<MultipartFile> files) throws IOException {
        return photoService.insertAttendanceDetail(files, studentId, "history/students");
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
