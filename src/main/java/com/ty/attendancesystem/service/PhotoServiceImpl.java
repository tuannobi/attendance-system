package com.ty.attendancesystem.service;

import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.ty.attendancesystem.base.BaseServiceImpl;
import com.ty.attendancesystem.model.Photo;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.repository.PhotoRepository;
import com.ty.attendancesystem.repository.UserRepository;
import com.ty.attendancesystem.util.PhotoUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PhotoServiceImpl extends BaseServiceImpl<Photo, Long> implements PhotoService {

  private PhotoRepository photoRepository;
  private UserRepository userRepository;

  @Autowired
  public PhotoServiceImpl(PhotoRepository photoRepository, UserRepository userRepository){
    this.photoRepository=photoRepository;
    this.userRepository = userRepository;
  }

  @Override
  protected JpaRepository<Photo, Long> getRepository() {
    return this.photoRepository;
  }

  @Override
  @Transactional
  public Photo save(Photo photo) {
    photo.setCreateAt(new Date());
    return super.save(photo);
  }

  @Transactional
  public List<Photo> getPhotosByStudent(String studentId) {
      return photoRepository.getPhotosByStudentId(studentId);
  }

  @Transactional
  public List<Photo> insert(List<MultipartFile> files, String studentId) throws IOException {
    Optional<User> existedUser = userRepository.findById(studentId);
    List<PhotoUpload> photoUploads = mapping(files);
    List<Photo> photos = new ArrayList<>();
    for (PhotoUpload photoUpload: photoUploads) {
      Photo photo = upload(photoUpload, "students/"+studentId);
      photo.setStudent(existedUser.get());
      photos.add(photo);
    }
    existedUser.get().setPhotos(photos);
    userRepository.save(existedUser.get());
    return photos;
   }

   private List<PhotoUpload> mapping(List<MultipartFile> files) {
    List<PhotoUpload> photoUploads = new ArrayList<>();
    for (MultipartFile multipartFile: files) {
      PhotoUpload photoUpload = new PhotoUpload();
      photoUpload.setTitle(multipartFile.getOriginalFilename());
      photoUpload.setFile(multipartFile);
      photoUploads.add(photoUpload);
    }
    return photoUploads;
   }

   private Photo upload(PhotoUpload photoUpload, String urlFolder) throws IOException {
     Map uploadResult = null;
        if (photoUpload.getFile() != null && !photoUpload.getFile().isEmpty()) {
            uploadResult = Singleton.getCloudinary().uploader().upload(photoUpload.getFile().getBytes(),
                    ObjectUtils.asMap("folder",urlFolder,"resource_type", "auto"));
            photoUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                photoUpload.setVersion(new Long((Integer) version));
            } else {
                photoUpload.setVersion((Long) version);
            }

            photoUpload.setSignature((String) uploadResult.get("signature"));
            photoUpload.setFormat((String) uploadResult.get("format"));
            photoUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        Photo photo = new Photo();
        photo.setTitle(photoUpload.getTitle());
        photo.setUpload(photoUpload);
        photo.setCreateAt(new Date());
        photo.setImage(photoUpload.getUrl());
        return photo;
   }

   private String randomName() {
    return "";
   }
}
