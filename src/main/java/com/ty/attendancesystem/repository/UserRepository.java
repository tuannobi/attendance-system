package com.ty.attendancesystem.repository;

import com.ty.attendancesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
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
  int updateInformationUser(String id, LocalDate birthday, String fullName, String phone, String email);

  @Query(value = "select u.* from users u inner join users_role ur on u.id = ur.users_id " +
          "where ur.role_id= :roleId", nativeQuery = true)
  List<User> getUsersByRole(int roleId);
}