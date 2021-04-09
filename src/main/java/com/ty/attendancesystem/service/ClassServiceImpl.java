package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.ClassStatus;
import com.ty.attendancesystem.constant.RoleName;
import com.ty.attendancesystem.exception.ServiceException;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.repository.ClassRepository;
import com.ty.attendancesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ClassServiceImpl extends BaseServiceImpl<Class,String> implements ClassService {
  private ClassRepository classRepository;
  private UserRepository userRepository;

  @Autowired
  public ClassServiceImpl(ClassRepository classRepository, UserRepository userRepository){
    this.classRepository=classRepository;
    this.userRepository=userRepository;
  }

  @Override
  protected JpaRepository<Class, String> getRepository() {
    return this.classRepository;
  }

  @Transactional
  @Override
  public Class insert(Class clazz) {
    if (!checkifClassIsExisted(clazz)) {
      if (checkIfUserIsTeacher(clazz).get()){
        clazz.setStatus(ClassStatus.OPENED);
        return classRepository.save(clazz);
      } else {
        throw new ServiceException("User is not Teacher role");
      }
    } else {
      throw new ServiceException("Class is existed");
    }
  }

  @Transactional
  @Override
  public Class update(Class clazz) {
    return null;
  }

  private AtomicBoolean checkIfUserIsTeacher(Class clazz){
    AtomicBoolean result = new AtomicBoolean(true);
    Optional<User> user = userRepository.findById(clazz.getTeacher().getId());
    if (user.isPresent()) {
      user.get().getRoles().forEach(role -> {
        if (!role.getName().equals(RoleName.ROLE_TEACHER.toString())) {
          result.set(false);
        }
      });
    } else {
      throw new ServiceException("User is not existed");
    }
    return result;
  }

  private boolean checkifClassIsExisted(Class clazz) {
    return classRepository.existsById(clazz.getId());
  }


}
