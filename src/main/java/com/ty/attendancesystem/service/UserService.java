package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User, String> {
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  int updateInformationUser(String id, LocalDate birthDay, String fullName, String phone, String email);

  User insert(User user);
  List<User> getUsersByRole(int id);
  void importUsers(MultipartFile multipartFile) throws IOException;
}
