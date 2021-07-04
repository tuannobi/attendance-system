package com.ty.attendancesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JoinColumn(name = "class_id")
    private Class clazz;

    //additional fields
    @Column(name = "lesson_start")
    private int start;

    @Column(name = "lesson_end")
    private int end;

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

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
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

    @Override
    public String toString() {
        return "TimeTableCourse{" +
                "id=" + id +
                ", timeTable=" + timeTable +
                ", clazz=" + clazz +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
