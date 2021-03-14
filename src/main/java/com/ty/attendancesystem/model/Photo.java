package com.ty.attendancesystem.model;

import com.cloudinary.StoredFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "photo")
public class Photo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "image")
  private String image;

  @Temporal(TemporalType.DATE)
  @Column(name = "create_at")
  private Date createAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Date createAt) {
    this.createAt = createAt;
  }

  public StoredFile getUpload() {
    StoredFile file = new StoredFile();
    file.setPreloadedFile(image);
    return file;
  }

  public void setUpload(StoredFile file) {
    this.image = file.getPreloadedFile();
  }
}
