package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Admin;
import com.ty.attendancesystem.model.Parent;
import com.ty.attendancesystem.repository.AdminRepository;
import com.ty.attendancesystem.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentServiceImpl extends BaseServiceImpl<Parent,Long> implements ParentService {

  private ParentRepository parentRepository;

  @Autowired
  public ParentServiceImpl(ParentRepository parentRepository){
    this.parentRepository = parentRepository;
  }

  @Override
  protected JpaRepository<Parent, Long> getRepository() {
    return this.parentRepository;
  }
}
