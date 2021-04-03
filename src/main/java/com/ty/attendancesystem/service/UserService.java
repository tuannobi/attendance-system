package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.User;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  int updateInformationUser(Long id, LocalDate birthDay, String fullName, String phone, String email);
}
