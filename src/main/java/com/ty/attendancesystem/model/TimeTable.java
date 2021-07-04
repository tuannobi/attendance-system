package com.ty.attendancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "time_table")
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "users_id")
    private String userId;

    @Column(name = "year")
    private int year;

    @Column(name = "semester")
    private int semester;

    @Column(name = "status")
    private int status;

    @Column(name="day_of_week")
    private int dayOfWeek;

    @OneToMany(mappedBy = "timeTable",cascade = CascadeType.ALL)
    private List<TimeTableCourse> timeTableCourses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TimeTableCourse> getTimeTableCourses() {
        return timeTableCourses;
    }

    public void setTimeTableCourses(List<TimeTableCourse> timeTableCourses) {
        this.timeTableCourses = timeTableCourses;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                ", status=" + status +
                ", dayOfWeek=" + dayOfWeek +
                ", timeTableCourses=" + timeTableCourses +
                '}';
    }
}
