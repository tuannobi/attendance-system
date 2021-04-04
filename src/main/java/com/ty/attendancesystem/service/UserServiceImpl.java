package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.exception.ServiceException;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.repository.UserRepository;
import com.ty.attendancesystem.util.DateTimeConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService{

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected JpaRepository<User, Long> getRepository() {
    return this.userRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Transactional(readOnly = true)
  @Override
  public Boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Transactional(readOnly = true)
  @Override
  public Boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Transactional
  @Override
  public int updateInformationUser(Long id, LocalDate birthDay, String fullName, String phone, String email) {
    String stringBirthDay = DateTimeConvertUtil.convertLocalDateToString(birthDay);
    System.out.println(stringBirthDay);
    return userRepository.updateInformationUser(id, birthDay, fullName, phone, email);
  }

  //Insert
  @Transactional
  @Override
  public User save(User user) {
    Boolean isDuplicateUsername = userRepository.existsByUsername(user.getUsername());
    Boolean isDuplicateEmail = userRepository.existsByEmail(user.getEmail());
    Boolean isDuplicatePhone = userRepository.existsByPhone(user.getPhone());
    if (isDuplicateUsername) {
      throw new ServiceException("Username is existed");
    }
    if (isDuplicateEmail) {
      throw new ServiceException("Email is existed");
    }
    if (isDuplicatePhone) {
      throw new ServiceException("Phone is existed");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return super.save(user);
  }

  @Transactional
  @Override
  public void delete(User user) {
    Boolean isExistedUser = userRepository.existsById(user.getId());
    if (!isExistedUser) {
      throw new ServiceException("User is not existed");
    }
    super.delete(user);
  }
}
