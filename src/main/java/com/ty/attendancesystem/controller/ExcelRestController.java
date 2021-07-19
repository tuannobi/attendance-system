package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.helper.ExcelImportHelper;
import com.ty.attendancesystem.helper.export.ClassExcelExporter;
import com.ty.attendancesystem.helper.export.CourseExcelExporter;
import com.ty.attendancesystem.helper.export.UserExcelExporter;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.Course;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelRestController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;

    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private UserService userService;

    @PostMapping("/courses")
    public ResponseEntity<?> importCourses(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelImportHelper.hasExcelFormat(multipartFile)){
            courseService.save(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/classes")
    public ResponseEntity<?> importClasses(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelImportHelper.hasExcelFormat(multipartFile)){
            classService.save(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/timetables")
    public ResponseEntity<?> importTimeTable(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelImportHelper.hasExcelFormat(multipartFile)){
            timeTableService.save(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/classes/student")
    public ResponseEntity<?> importStudentsToClass(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelImportHelper.hasExcelFormat(multipartFile)){
            studentClassService.importStudents(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> importUsers(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        if(ExcelImportHelper.hasExcelFormat(multipartFile)){
            userService.importUsers(multipartFile);
        }
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.ADD_SUCCESS), HttpStatus.OK);
    }

    //import
    @GetMapping("/export/classes")
    public void exportClassesToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=classes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Class> classes = classService.findAll();

        ClassExcelExporter excelExporter = new ClassExcelExporter(classes);

        excelExporter.export(response);
    }

    @GetMapping("/export/courses")
    public void exportCoursesToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=courses_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Course> courses = courseService.findAll();

        CourseExcelExporter courseExcelExporter = new CourseExcelExporter(courses);

        courseExcelExporter.export(response);
    }

    @GetMapping("/export/users/teacher")
    public void exportUserRoleTeacherToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users_teacher_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> users = userService.getUsersByRole(2);

        UserExcelExporter userExcelExporter = new UserExcelExporter(users);

        userExcelExporter.export(response);
    }

    @GetMapping("/export/users/parent")
    public void exportUserRoleParentToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users_parent_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> users = userService.getUsersByRole(3);

        UserExcelExporter userExcelExporter = new UserExcelExporter(users);

        userExcelExporter.export(response);
    }

    @GetMapping("/export/users/student")
    public void exportUserRoleStudentToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users_student_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> users = userService.getUsersByRole(4);

        UserExcelExporter userExcelExporter = new UserExcelExporter(users);

        userExcelExporter.export(response);
    }

    @GetMapping("/export/users")
    public void exportUsersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Users_all_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> users = userService.findAll();

        UserExcelExporter userExcelExporter = new UserExcelExporter(users);

        userExcelExporter.export(response);
    }

}
