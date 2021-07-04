package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.constant.ClassStatus;
import com.ty.attendancesystem.constant.RoleName;
import com.ty.attendancesystem.exception.ServiceException;
import com.ty.attendancesystem.helper.ExcelHelper;
import com.ty.attendancesystem.model.Class;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.repository.ClassRepository;
import com.ty.attendancesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
    if (!checkIfClassIsExisted(clazz)) {
      if (checkIfClassHasUserIsTeacher(clazz).get()){
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
      if (checkIfClassHasUserIsTeacher(clazz).get()){
        clazz.setStatus(ClassStatus.OPENED);
        return classRepository.save(clazz);
      } else {
        throw new ServiceException("User is not Teacher role");
      }
  }

  @Transactional(readOnly = true)
  @Override
  public List<String> findClassIdByTeacherUsername(String id) {
    if (!checkIfUsernameIsTeacher(id).get()){
      throw new ServiceException("User is not Teacher role");
    }
    return classRepository.findClassIdByTeacherUsername(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Class> findClassesByCourseId(String courseId) {
    return classRepository.findClassesByCourse_Id(courseId);
  }

  @Override
  public void save(MultipartFile multipartFile) throws IOException {
    List<Class> classes = ExcelHelper.excelToClasses(multipartFile.getInputStream());
    classRepository.saveAll(classes);
  }

  private AtomicBoolean checkIfClassHasUserIsTeacher(Class clazz){
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

  private AtomicBoolean checkIfUsernameIsTeacher(String username){
    AtomicBoolean result = new AtomicBoolean(true);
    Optional<User> user = userRepository.findByUsername(username);
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

  private boolean checkIfClassIsExisted(Class clazz) {
    return classRepository.existsById(clazz.getId());
  }


}
