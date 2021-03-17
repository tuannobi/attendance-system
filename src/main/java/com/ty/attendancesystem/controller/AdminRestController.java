package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.exception.ValidateException;
import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {
  private AdminService adminService;

  @Autowired
  public AdminRestController(AdminService adminService){
    this.adminService = adminService;
  }

  @GetMapping
  public ResponseEntity<?> getAll(){
    List<Admin> admins = adminService.findAll();
    return new ResponseEntity<>(admins, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable("id") Long id){
    Optional<Admin> admin = adminService.findById(id);
    if (!admin.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(admin,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody Admin admin){
    adminService.save(admin);
    return new ResponseEntity<>(admin, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody Admin admin){
    adminService.save(admin);
    return new ResponseEntity<>(admin, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<?> delete(@RequestBody Admin admin){
    adminService.delete(admin);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
