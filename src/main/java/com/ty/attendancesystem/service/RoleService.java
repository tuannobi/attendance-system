package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.model.Role;

import java.util.Optional;

public interface RoleService extends BaseService<Role, Long> {
  Optional<Role> findByName(String name);
}
