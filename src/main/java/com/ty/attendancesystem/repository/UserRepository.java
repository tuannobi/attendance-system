package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  Boolean existsByPhone(String phone);

  @Modifying
  @Query(value = "update User set email = :email," +
          " phone = :phone," +
          " fullName = :fullName," +
          " birthday = :birthday" +
          " where id = :id")
  int updateInformationUser(Long id, LocalDate birthday, String fullName, String phone, String email);
}
