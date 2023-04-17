package com.example.course_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    private String courseID;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "day")
    private Integer day;
    @Column(name = "start_time")
    private Integer startTime;
    @Column(name = "end_time")
    private Integer endTime;
    @Column(name = "credit")
    private Integer credit;

//    Constructor
    public Course() {}

    public Course(String courseID, String courseName, Integer day, Integer startTime, Integer endTime, Integer credit) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.credit = credit;
    }


//    Getter
    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public Integer getCredit() {
        return credit;
    }

//    Setter
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
