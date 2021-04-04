package com.ty.attendancesystem.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    private String id;

    @Column(name = "name")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
