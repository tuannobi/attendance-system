package com.ty.attendancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.runtime.AllocationStrategy;

import javax.persistence.*;

@Entity
@Table(name = "time_table_course")
//@JsonIgnoreProperties(value = {"timeTable"})
public class TimeTableCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_table_id")
    @JsonIgnore
    private TimeTable timeTable;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //additional fields
    @Column(name = "lesson_start")
    private int start;

    @Column(name = "lesson_end")
    private int end;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    public TimeTableCourse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "TimeTableCourse{" +
                "id=" + id +
                ", timeTable=" + timeTable +
                ", course=" + course +
                ", start=" + start +
                ", end=" + end +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
