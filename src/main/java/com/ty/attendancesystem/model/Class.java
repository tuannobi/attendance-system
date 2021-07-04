package com.ty.attendancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "class")
public class Class {
    @Id
    private String id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "number_student")
    private long numberStudent;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "clazz")
    @JsonIgnore
    private List<TimeTableCourse> timeTableCourses;

    @ManyToOne
    @JoinColumn(name = "teacher_user_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Class() {
    }

    public Class(String id) {
        this.id = id;
    }

    public List<TimeTableCourse> getTimeTableCourses() {
        return timeTableCourses;
    }

    public void setTimeTableCourses(List<TimeTableCourse> timeTableCourses) {
        this.timeTableCourses = timeTableCourses;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(long numberStudent) {
        this.numberStudent = numberStudent;
    }
}
