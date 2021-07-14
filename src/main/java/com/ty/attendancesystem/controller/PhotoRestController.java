package com.ty.attendancesystem.controller;

import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.ty.attendancesystem.model.Photo;
import com.ty.attendancesystem.service.PhotoService;
import com.ty.attendancesystem.util.PhotoUpload;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/photos")
public class PhotoRestController {
  private PhotoService photoService;

  public PhotoRestController(PhotoService photoService){
    this.photoService=photoService;
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public Object listPhotos(){
    return photoService.findAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public List<Photo> get(@PathVariable("id") String id) {
    return photoService.getPhotosByStudent(id);
  }

  @GetMapping("/list/{id}")
  public List<String> getList(@PathVariable("id") String id) {
    List<String> photoList = new ArrayList<>();
    List<Photo> photos = photoService.getPhotosByStudent(id);
    for(Photo photo: photos) {
      photoList.add(photo.getImage());
    }
    return photoList;
  }

  @SuppressWarnings("rawtypes")
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public String uploadPhoto(PhotoUpload photoUpload) throws IOException {
    Map uploadResult = null;
    if (photoUpload.getFile() != null && !photoUpload.getFile().isEmpty()) {
      uploadResult = Singleton.getCloudinary().uploader().upload(photoUpload.getFile().getBytes(),
          ObjectUtils.asMap("folder","attendance-system","resource_type", "auto"));
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
      photoService.save(photo);
      return photoUpload.getUrl();
    }
}
