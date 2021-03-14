package com.ty.attendancesystem.service;

import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Photo;
import com.ty.attendancesystem.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PhotoServiceImpl extends BaseServiceImpl<Photo, Long> implements PhotoService {

  private PhotoRepository photoRepository;

  @Autowired
  public PhotoServiceImpl(PhotoRepository photoRepository){
    this.photoRepository=photoRepository;
  }

  @Override
  protected JpaRepository<Photo, Long> getRepository() {
    return this.photoRepository;
  }

  @Override
  public Photo save(Photo photo) {
    photo.setCreateAt(new Date());
    return super.save(photo);
  }
}
