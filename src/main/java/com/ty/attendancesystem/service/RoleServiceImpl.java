package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseService;
import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Role;
import com.ty.attendancesystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements RoleService {
  private RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository){
    this.roleRepository=roleRepository;
  }

  @Override
  protected JpaRepository<Role, Long> getRepository() {
    return this.roleRepository;
  }

  @Override
  public Optional<Role> findByName(String name) {
    return roleRepository.findByName(name);
  }
}
