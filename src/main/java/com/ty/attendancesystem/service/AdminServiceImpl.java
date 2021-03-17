package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Role;
import com.ty.attendancesystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin,Long> implements AdminService {

  private AdminRepository adminRepository;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder){
    this.adminRepository = adminRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected JpaRepository<Admin, Long> getRepository() {
    return this.adminRepository;
  }

  @Override
  public Admin save(Admin admin) {
    return admin;
  }
}
