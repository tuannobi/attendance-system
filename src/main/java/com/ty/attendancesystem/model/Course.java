package com.ty.attendancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@JsonIgnoreProperties(value = {"timeTableCourses"})
public class Course {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    public Course() {
    }

    public Course(String id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<TimeTableCourse> timeTableCourses;

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

    public List<TimeTableCourse> getTimeTableCourses() {
        return timeTableCourses;
    }

    public void setTimeTableCourses(List<TimeTableCourse> timeTableCourses) {
        this.timeTableCourses = timeTableCourses;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", timeTableCourses=" + timeTableCourses +
                '}';
    }
}
