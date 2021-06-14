package com.ty.attendancesystem.controller;

import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.ty.attendancesystem.constant.ResponseMessage;
import com.ty.attendancesystem.constant.RoleNumber;
import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.message.SuccessResponse;
import com.ty.attendancesystem.model.Photo;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.service.PhotoService;
import com.ty.attendancesystem.service.UserService;
import com.ty.attendancesystem.util.PhotoUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserRestController {

    public final UserService userService;

    private PhotoService photoService;

    @Autowired
    public UserRestController(UserService userService, PhotoService photoService){
        this.userService = userService;
        this.photoService = photoService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.findAll();
    }

    @GetMapping("/students")
    public List<User> getStudents(){
        return userService.getUsersByRole(RoleNumber.ROLE_STUDENT);
    }

    @PostMapping("/students/photos")
    public List<Photo> addPhotosToStudent(@RequestParam(name = "id") String studentId, List<MultipartFile> files) throws IOException {
        return photoService.insert(files, studentId);
    }

    @GetMapping("/students/photos")
    public List<Photo> getPhotosByStudent(@RequestParam(name = "id") String studentId) {
        return photoService.getPhotosByStudent(studentId);
    }

    @GetMapping("/teachers")
    public List<User> getTeachers(){
        return userService.getUsersByRole(RoleNumber.ROLE_TEACHER);
    }

    @GetMapping("/admins")
    public List<User> getAdmins(){
        return userService.getUsersByRole(RoleNumber.ROLE_ADMIN);
    }

    @GetMapping("/parents")
    public List<User> getParents(){
        return userService.getUsersByRole(RoleNumber.ROLE_PARENT);
    }

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable("id") String id){
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody User user){
        // Not yet validate for email pattern
        validateAddUser(user);
        User savedUser = userService.insert(user);
        return new ResponseEntity<>(new SuccessResponse(savedUser,
                HttpStatus.CREATED.value(),
                ResponseMessage.ADD_SUCCESS),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateInfo(@RequestBody User user){
        validateUpdateInfoUser(user);
        userService.updateInformationUser(user.getId(),
                user.getBirthday(),
                user.getFullName(),
                user.getPhone(),
                user.getEmail());
        return new ResponseEntity<>(new SuccessResponse("",
                    HttpStatus.OK.value(),
                    ResponseMessage.UPDATE_SUCCESS), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody User user){
        validateDeleteUser(user);
        userService.delete(user);
        return new ResponseEntity<>(new SuccessResponse("",
                HttpStatus.OK.value(),
                ResponseMessage.DELETE_SUCCESS), HttpStatus.OK);
    }

    private void validateAddUser(User user){
        if (user.getId() == null) {
            throw new ValidateException("Id field must not be null");
        }
        if (user.getUsername() == null) {
            throw new ValidateException("Username field must not be null");
        }
        if (user.getId().trim().isEmpty()) {
            throw new ValidateException("Id field must not be blank");
        }
        if (user.getUsername().trim().isEmpty()) {
            throw new ValidateException("Username field must not be blank");
        }
        if (user.getPassword() == null) {
            throw new ValidateException("Password field must not be null");
        }
        if (user.getPassword().trim().isEmpty()) {
            throw new ValidateException("Password field must not be blank");
        }
        if (user.getEmail() == null) {
            throw new ValidateException("Email field must not be null");
        }
        if (user.getEmail().trim().isEmpty()) {
            throw new ValidateException("Email field must not be blank");
        }
        if (user.getPhone() == null) {
            throw new ValidateException("Phone field must not be null");
        }
        if (user.getPhone().trim().isEmpty()) {
            throw new ValidateException("Phone field must not be blank");
        }
        if (user.getFullName() == null) {
            throw new ValidateException("Full name field must not be null");
        }
        if (user.getFullName().trim().isEmpty()) {
            throw new ValidateException("Full name field must not be blank");
        }
        if (user.getBirthday() == null) {
            throw new ValidateException("Birthday field must not be null");
        }
        if (user.getRoles() == null) {
            throw new ValidateException("Role must not be null");
        }
        user.getRoles().forEach(role -> {
            if (role.getId() == null){
                throw new ValidateException("Role id field must not be null");
            }
        });
    }

    private void validateUpdateInfoUser(User user){
        if(user.getId() == null) {
            throw new ValidateException("Id field must not be null");
        }
    }

    private void validateDeleteUser(User user) {
        if (user.getId() == null) {
            throw new ValidateException("Id field must not be null");
        }
    }
}
