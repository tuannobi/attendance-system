package com.ty.attendancesystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
  @GetMapping("/rest/test/area1")
  @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
  public String userAccess() {
    return ">>> This is for Teacher or Admin";
  }

  @GetMapping("/rest/test/area2")
  @PreAuthorize("hasRole('STUDENT')")
  public String projectManagementAccess() {
    return ">>> This is only for Student";
  }

  @GetMapping("/rest/test/area3")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return ">>> This is only for Admin";
  }
}
